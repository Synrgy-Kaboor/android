package com.synrgy.kaboor.payment

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.Constant
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.enums.ClipboardType
import com.synrgy.common.utils.ext.PermissionExt
import com.synrgy.common.utils.ext.chuckedString
import com.synrgy.common.utils.ext.copyTextToClipboard
import com.synrgy.common.utils.ext.getImageFile
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.requestMultiplePermission
import com.synrgy.common.utils.ext.setTimer
import com.synrgy.common.utils.ext.showHideToggle
import com.synrgy.common.utils.ext.snackbarDanger
import com.synrgy.common.utils.ext.toCountDownGmt7
import com.synrgy.common.utils.ext.toCurrency
import com.synrgy.common.utils.ext.toMinutes
import com.synrgy.common.utils.ext.toStringTrim
import com.synrgy.domain.booking.model.request.ProofParam
import com.synrgy.domain.booking.model.request.UpdateProofParam
import com.synrgy.domain.promo.model.response.Bank
import com.synrgy.kaboor.R
import com.synrgy.kaboor.base.MainActivity
import com.synrgy.kaboor.booking.flight.FlightScheduleActivity
import com.synrgy.kaboor.databinding.ActivityPaymentMethodDetailBinding
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import org.koin.android.ext.android.inject

class PaymentMethodDetailActivity : KaboorActivity<ActivityPaymentMethodDetailBinding>() {

    companion object {
        fun start(
            context: Context,
            bookingId: Int,
            bank: Bank?
        ) {
            context.startActivity(Intent(context, PaymentMethodDetailActivity::class.java).apply {
                putExtra(ConstantKey.KEY_BOOKING_ID, bookingId)
                putExtra(ConstantKey.KEY_BANK, bank)
            })
        }
    }

    private val viewModel: BookingViewModel by inject()

    private var bank: Bank? = null
    private var bookingId: Int = 0
    private var atmState = false
    private var internetBankingState = false
    private var mobileBankingState = false
    private var isPaymentComplete = false

    private lateinit var countDown: CountDownTimer

    override fun getViewBinding(): ActivityPaymentMethodDetailBinding {
        return ActivityPaymentMethodDetailBinding.inflate(layoutInflater)
    }

    override fun initIntent() {
        super.initIntent()

        bookingId = intent.getIntExtra(ConstantKey.KEY_BOOKING_ID, 0)
        bank = intent.getParcelableExtra(ConstantKey.KEY_BANK)
    }

    override fun initUI() {
        with(binding) {
            val atm = resources.getStringArray(bank?.atm ?: 0)
            val internet = resources.getStringArray(bank?.internet ?: 0)
            val mobile = resources.getStringArray(bank?.mobile ?: 0)

            atm.forEach {
                tvAtmInstruction.append(
                    getString(
                        R.string.format_bullet_point,
                        "$it\n"
                    )
                )
            }
            mobile.forEach {
                tvMobileInstruction.append(
                    getString(
                        R.string.format_bullet_point,
                        "$it\n"
                    )
                )
            }
            internet.forEach {
                tvInternetInstruction.append(
                    getString(
                        R.string.format_bullet_point,
                        "$it\n"
                    )
                )
            }
        }
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        imgCopyAccountNumber.onClick {
            copyTextToClipboard(
                tvAccountNumber.toStringTrim(),
                ClipboardType.ACCOUNT_NUMBER
            )
        }

        imgCopyTotalPayment.onClick {
            copyTextToClipboard(
                tvTotalPayment.toStringTrim(),
                ClipboardType.TOTAL_PAYMENT
            )
        }

        toggleAtm.onClick {
            tvAtmInstruction.showHideToggle()
            atmState = !atmState
            toggleAtm.isSelected = atmState
        }

        toggleInternet.onClick {
            tvInternetInstruction.showHideToggle()
            internetBankingState = !internetBankingState
            toggleInternet.isSelected = internetBankingState
        }

        toggleMobile.onClick {
            tvMobileInstruction.showHideToggle()
            mobileBankingState = !mobileBankingState
            toggleMobile.isSelected = mobileBankingState
        }

        btnShowOrder.onClick { handleNavigation() }
        uploadReceipt.setOnSelectImage { requestPermissions() }
        uploadReceipt.setOnRemoveImage { showAlertRemoveImage() }
    }

    override fun initProcess() {
        super.initProcess()
        viewModel.getPaymentDetail(bookingId)
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.payment.observerLiveData(
            this,
            onLoading = ::showLoading,
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = { data ->
                hideLoading()
                with(binding) {
                    tvAccountNumber.text = data.accountNumber?.chuckedString()
                    tvTotalPayment.text = data.totalPrice?.toCurrency(false)
                    uploadReceipt.setUploaded(data.paymentCompleted ?: false)
                    initCountDown(timer = data.expiredTime?.toCountDownGmt7() ?: 0L)
                    isPaymentComplete = data.paymentCompleted ?: false
                    if (isPaymentComplete) btnShowOrder.text = getString(R.string.label_finish)
                }
            }
        )

        viewModel.uploadProof.observerLiveData(
            this,
            onLoading = ::showLoading,
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = { proof ->
                val body = UpdateProofParam(proof.fileName)
                viewModel.updateProof(bookingId, body)
            }
        )

        viewModel.generic.observerLiveData(
            this,
            onLoading = {},
            onFailure = { _, message -> showErrorDialog(message.toString()) },
            onSuccess = {
                hideLoading()
                binding.uploadReceipt.setUploaded(true)
                binding.btnShowOrder.text = getString(R.string.label_finish)
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::countDown.isInitialized) countDown.cancel()
    }

    private fun handleNavigation() {
        if (isPaymentComplete) {
            MainActivity.start(this)
            finishAffinity()
        } else {
            FlightScheduleActivity.start(this)
            finishAffinity()
        }
    }

    private fun initCountDown(timer: Long) {
        countDown = setTimer(
            millisTimer = timer,
            interval = Constant.TIMER_INTERVAL,
            onTick = { binding.tvTime.text = it.toMinutes() },
        )
        countDown.start()
    }

    private fun showAlertRemoveImage() {
        showAlertDialog(
            title = getString(R.string.title_alert_remove_image),
            description = getString(R.string.message_alert_remove_image),
            primaryTextButton = getString(R.string.label_cancel),
            secondaryTextButton = getString(R.string.label_remove),
            secondaryAction = { binding.uploadReceipt.removeImageFile() }
        )
    }

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                val file = getImageFile(uri)
                viewModel.uploadProof(ProofParam(file))
            } else snackbarDanger(getString(R.string.message_failed_select_image))
        }

    private fun launchPickMedia() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            requestMultiplePermission(
                permissions = PermissionExt.imagePermissionsAndroid14,
                requestCode = PermissionExt.IMAGE_REQUEST_CODE,
                doIfGranted = ::launchPickMedia
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestMultiplePermission(
                permissions = PermissionExt.imagePermissionsAndroid13,
                requestCode = PermissionExt.IMAGE_REQUEST_CODE,
                doIfGranted = ::launchPickMedia
            )
        } else {
            requestMultiplePermission(
                permissions = PermissionExt.imagePermissionAndroid12L,
                requestCode = PermissionExt.IMAGE_REQUEST_CODE,
                doIfGranted = ::launchPickMedia
            )
        }
    }
}
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
import com.synrgy.common.utils.enums.BankType
import com.synrgy.common.utils.enums.ClipboardType
import com.synrgy.common.utils.enums.PaymentType
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
import com.synrgy.domain.promo.mapper.toDomain
import com.synrgy.domain.promo.model.response.Bank
import com.synrgy.kaboor.R
import com.synrgy.kaboor.base.MainActivity
import com.synrgy.kaboor.databinding.ActivityPaymentMethodDetailBinding
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import org.koin.android.ext.android.inject

class PaymentMethodDetailActivity : KaboorActivity<ActivityPaymentMethodDetailBinding>() {

    companion object {
        fun start(
            context: Context,
            bookingId: Int,
            type: PaymentType = PaymentType.NORMAL,
            uploadedProof: Boolean = false
        ) {
            context.startActivity(Intent(context, PaymentMethodDetailActivity::class.java).apply {
                putExtra(ConstantKey.KEY_BOOKING_ID, bookingId)
                putExtra(ConstantKey.KEY_PAYMENT_TYPE, type)
                putExtra(ConstantKey.KEY_UPLOADED_PROOF, uploadedProof)
            })
        }
    }

    private val viewModel: PaymentViewModel by inject()

    private var bookingId: Int = 0
    private var paymentType: PaymentType = PaymentType.NORMAL
    private var uploadedProof = false

    private var atmState = false
    private var internetBankingState = false
    private var mobileBankingState = false
    private var isPaymentComplete = false
    private var canUploadProof = true

    private lateinit var countDown: CountDownTimer

    override fun getViewBinding(): ActivityPaymentMethodDetailBinding {
        return ActivityPaymentMethodDetailBinding.inflate(layoutInflater)
    }

    override fun initIntent() {
        super.initIntent()

        bookingId = intent.getIntExtra(ConstantKey.KEY_BOOKING_ID, 0)
        uploadedProof = intent.getBooleanExtra(ConstantKey.KEY_UPLOADED_PROOF, false)
        paymentType = intent.getSerializableExtra(ConstantKey.KEY_PAYMENT_TYPE) as PaymentType
    }

    override fun initUI() {}

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { handleNavigation() }
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
        uploadReceipt.setOnRemoveImage { showAlertRemoveImage() }
        uploadReceipt.setOnSelectImage {
            if (canUploadProof) requestPermissions()
            else snackbarDanger(getString(R.string.message_expired_time))
        }
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
                    uploadReceipt.setUploaded(data.paymentCompleted ?: false || uploadedProof)
                    initCountDown(timer = data.expiredTime?.toCountDownGmt7() ?: 0L)
                    isPaymentComplete = data.paymentCompleted ?: false
                    if (isPaymentComplete || uploadedProof) btnShowOrder.text = getString(R.string.label_finish)

                    val bank = BankType.getBankType(data.methodName.toString()).bank.toDomain()
                    handleInstructionBank(bank)
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
        if (paymentType == PaymentType.NORMAL) {
            MainActivity.start(this)
            finishAffinity()
        } else onBackPress()
    }

    private fun initCountDown(timer: Long) {
        countDown = setTimer(
            millisTimer = timer,
            interval = Constant.TIMER_INTERVAL,
            onTick = { binding.tvTime.text = it.toMinutes() },
            onFinish = {
                binding.tvTime.text = "00:00:00"
                canUploadProof = false
            }
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

    private fun handleInstructionBank(bank: Bank) {
        with(binding) {
            val atm = resources.getStringArray(bank.atm)
            val internet = resources.getStringArray(bank.internet)
            val mobile = resources.getStringArray(bank.mobile)

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
}
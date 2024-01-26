package com.synrgy.kaboor.payment

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.enums.ClipboardType
import com.synrgy.common.utils.ext.PermissionExt
import com.synrgy.common.utils.ext.copyTextToClipboard
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.requestMultiplePermission
import com.synrgy.common.utils.ext.setTimer
import com.synrgy.common.utils.ext.showHideToggle
import com.synrgy.common.utils.ext.snackbarDanger
import com.synrgy.common.utils.ext.toMinutes
import com.synrgy.common.utils.ext.toStringTrim
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityPaymentMethodDetailBinding
import com.wahidabd.library.utils.exts.onClick

class PaymentMethodDetailActivity : KaboorActivity<ActivityPaymentMethodDetailBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, PaymentMethodDetailActivity::class.java))
        }
    }


    private var atmState = false
    private var internetBankingState = false
    private var mobileBankingState = false

    private val countDown = setTimer(
        millisTimer = 300000L,
        interval = 1000L,
        onTick = { binding.tvTime.text = it.toMinutes() }
    )

    override fun getViewBinding(): ActivityPaymentMethodDetailBinding {
        return ActivityPaymentMethodDetailBinding.inflate(layoutInflater)
    }

    override fun initUI() = with(binding) {
        // TODO: Remove this after API ready
        tvAccountNumber.text = "1420 2010 0098 2336"
        tvTotalPayment.text = "1.000.000"

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

        uploadReceipt.setOnSelectImage { requestPermissions() }
        uploadReceipt.setOnRemoveImage { showAlertRemoveImage() }
    }

    override fun initProcess() {
        super.initProcess()
        countDown.start()
    }

    override fun onStop() {
        super.onStop()
        countDown.cancel()
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
            if (uri != null) binding.uploadReceipt.setImageFile(uri)
            else snackbarDanger(getString(R.string.message_failed_select_image))
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
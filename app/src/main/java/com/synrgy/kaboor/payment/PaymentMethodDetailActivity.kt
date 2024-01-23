package com.synrgy.kaboor.payment

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.enums.ClipboardType
import com.synrgy.common.utils.ext.copyTextToClipboard
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.showHideToggle
import com.synrgy.common.utils.ext.toStringTrim
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
    }
}
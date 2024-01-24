package com.synrgy.kaboor.booking

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.enums.ProtectionType
import com.synrgy.kaboor.booking.dialog.ExtraProtectionBottomSheetFragment
import com.synrgy.kaboor.databinding.ActivityExtraProtectionBinding
import com.synrgy.kaboor.payment.PaymentMethodActivity
import com.synrgy.kaboor.utils.constant.ConstantTag
import com.wahidabd.library.utils.exts.onClick

class ExtraProtectionActivity : KaboorActivity<ActivityExtraProtectionBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ExtraProtectionActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityExtraProtectionBinding {
        return ActivityExtraProtectionBinding.inflate(layoutInflater)
    }

    override fun initUI() {
    }

    override fun initAction() = with(binding) {
        travelInsurance.setOnLoadMoreClickListener { showProtectionDialog(ProtectionType.INSURANCE) }
        baggageInsurance.setOnLoadMoreClickListener { showProtectionDialog(ProtectionType.BAGGAGE) }
        delayProtection.setOnLoadMoreClickListener { showProtectionDialog(ProtectionType.DELAY) }
        btnPay.onClick { PaymentMethodActivity.start(this@ExtraProtectionActivity) }
    }

    private fun showProtectionDialog(type: ProtectionType) {
        ExtraProtectionBottomSheetFragment.newInstance(type)
            .show(supportFragmentManager, ConstantTag.TAG_INSURANCE)
    }

}
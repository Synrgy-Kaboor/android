package com.synrgy.kaboor.booking

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.Constant
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.constant.ConstantTag
import com.synrgy.common.utils.enums.ProtectionType
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.toCurrency
import com.synrgy.domain.booking.model.request.BookingParam
import com.synrgy.kaboor.booking.dialog.ExtraProtectionBottomSheetFragment
import com.synrgy.kaboor.databinding.ActivityExtraProtectionBinding
import com.synrgy.kaboor.payment.PaymentMethodActivity
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.orZero

class ExtraProtectionActivity : KaboorActivity<ActivityExtraProtectionBinding>() {

    companion object {
        fun start(
            context: Context,
            bookingParam: BookingParam,
            price: Long
        ) {
            context.startActivity(Intent(context, ExtraProtectionActivity::class.java).apply {
                putExtra(ConstantKey.KEY_BOOKING_PARAM, bookingParam)
                putExtra(ConstantKey.KEY_PRICE, price)
            })
        }
    }

    private var bookingParam: BookingParam? = null
    private var price: Long = 0L

    override fun getViewBinding(): ActivityExtraProtectionBinding {
        return ActivityExtraProtectionBinding.inflate(layoutInflater)
    }

    override fun initIntent() {
        super.initIntent()

        bookingParam = intent.getParcelableExtra(ConstantKey.KEY_BOOKING_PARAM)
        price = intent.getLongExtra(ConstantKey.KEY_PRICE, 0L)
    }

    override fun initUI() = with(binding) {
        tvTotalPrice.text = price.toCurrency()
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        travelInsurance.apply {
            setOnAddonClickList { isSelected -> calculateInsurance(isSelected) }
            setOnLoadMoreClickListener { showProtectionDialog(ProtectionType.INSURANCE) }
        }
        baggageInsurance.apply {
            setOnAddonClickList { isSelected -> calculateInsurance(isSelected) }
            setOnLoadMoreClickListener { showProtectionDialog(ProtectionType.BAGGAGE) }
        }
        delayProtection.apply {
            setOnAddonClickList { isSelected -> calculateInsurance(isSelected) }
            setOnLoadMoreClickListener { showProtectionDialog(ProtectionType.DELAY) }
        }
        btnPay.onClick { handleInsurance() }
    }

    private fun handleInsurance() = with(binding){
        val body = bookingParam?.copy(
            addDelayProtection = delayProtection.isSelected,
            addBaggageInsurance = baggageInsurance.isSelected,
            addTravelInsurance = travelInsurance.isSelected
        )

        PaymentMethodActivity.start(this@ExtraProtectionActivity, price, body)
    }

    private fun showProtectionDialog(type: ProtectionType) {
        ExtraProtectionBottomSheetFragment.newInstance(type)
            .show(supportFragmentManager, ConstantTag.TAG_INSURANCE)
    }

    private fun calculateInsurance(times: Boolean) = with(binding) {
        if (times) {
            price += (bookingParam?.passengers?.size.orZero()) * Constant.INSURANCE_PRICE
        } else {
            price -= (bookingParam?.passengers?.size.orZero()) * Constant.INSURANCE_PRICE
        }

        tvTotalPrice.text = price.toCurrency()
    }

}
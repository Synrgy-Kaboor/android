package com.synrgy.kaboor.payment

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.domain.payment.Bank
import com.synrgy.kaboor.databinding.ActivityPaymentMethodBinding
import com.synrgy.kaboor.payment.adapter.PaymentMethodAdapter
import com.synrgy.kaboor.promo.VoucherBottomSheetFragment
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.synrgy.kaboor.utils.constant.ConstantTag
import com.wahidabd.library.utils.exts.enable
import com.wahidabd.library.utils.exts.onClick

class PaymentMethodActivity : KaboorActivity<ActivityPaymentMethodBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, PaymentMethodActivity::class.java))
        }
    }

    private val paymentMethodAdapter by lazy {
        PaymentMethodAdapter(this) { type ->
            onSave.invoke(type)
            binding.btnPay.enable()
        }
    }

    private var onSave: (Bank) -> Unit = {}
    override fun getViewBinding(): ActivityPaymentMethodBinding =
        ActivityPaymentMethodBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() = with(binding) {
        tvPrice.text = "Rp 1.250.000"
        initPaymentMethod()
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        voucherContainer.onClick { showVoucher() }
        btnPay.onClick { PaymentMethodDetailActivity.start(this@PaymentMethodActivity) }
    }

    override fun initProcess() {
        paymentMethodAdapter.setData = ConstantDummy.bankPaymentMethod()
    }

    override fun initObservers() {}

    private fun initPaymentMethod() = with(binding) {
        val layoutManager =
            LinearLayoutManager(
                this@PaymentMethodActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        rvTicket.layoutManager = layoutManager
        rvTicket.adapter = paymentMethodAdapter
    }

    private fun showVoucher() {
        VoucherBottomSheetFragment.newInstance { voucher ->
            binding.tvVoucherCode.text = voucher.voucherCode
        }.show(supportFragmentManager, ConstantTag.TAG_VOUCHER)
    }
}
package com.synrgy.kaboor.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.domain.payment.Bank
import com.synrgy.kaboor.databinding.ActivityPaymentMethodBinding
import com.synrgy.kaboor.promo.VoucherBottomSheetFragment
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.synrgy.kaboor.utils.constant.ConstantTag
import com.wahidabd.library.utils.exts.onClick

class PaymentMethodActivity : KaboorActivity<ActivityPaymentMethodBinding>() {

    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, PaymentMethodActivity::class.java))
        }
    }

    private val paymentMethodAdapter by lazy {
        PaymentMethodAdapter(
            this
        ) { type ->
            onSave.invoke(type)
        }
    }

    private var onSave: (Bank) -> Unit = {}
    override fun getViewBinding(): ActivityPaymentMethodBinding =
        ActivityPaymentMethodBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {
        initPaymentMethod()
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        voucherContainer.onClick { showVoucher() }
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
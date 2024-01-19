package com.synrgy.kaboor.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.domain.payment.Bank
import com.synrgy.kaboor.databinding.ActivityPaymentMethodBinding
import com.synrgy.kaboor.utils.constant.ConstantDummy

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

    // TODO: For handle intent (Data, etc)
    override fun initIntent() {}

    // TODO: For UI
    override fun initUI() {
        initPaymentMethod()
    }

    // TODO: For Action (Click, Touch, etc)
    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
    }

    // TODO: For Process (API, Call ViewModel, etc)
    override fun initProcess() {
        paymentMethodAdapter.setData = ConstantDummy.bankPaymentMethod()
    }

    // TODO: For Observer (LiveData, etc)
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
}
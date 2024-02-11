package com.synrgy.kaboor.payment

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.Selectable
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.constant.ConstantTag
import com.synrgy.common.utils.enums.BankType
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.toCurrency
import com.synrgy.domain.booking.model.request.BookingParam
import com.synrgy.domain.promo.mapper.toDomain
import com.synrgy.domain.promo.model.response.Bank
import com.synrgy.domain.promo.model.response.Voucher
import com.synrgy.kaboor.databinding.ActivityPaymentMethodBinding
import com.synrgy.kaboor.payment.adapter.PaymentMethodAdapter
import com.synrgy.kaboor.promo.VoucherBottomSheetFragment
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.wahidabd.library.utils.exts.enable
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import org.koin.android.ext.android.inject

class PaymentMethodActivity : KaboorActivity<ActivityPaymentMethodBinding>() {

    companion object {
        fun start(
            context: Context,
            price: Long,
            bookingParam: BookingParam?,
        ) {
            context.startActivity(Intent(context, PaymentMethodActivity::class.java).apply {
                putExtra(ConstantKey.KEY_PRICE, price)
                putExtra(ConstantKey.KEY_BOOKING_PARAM, bookingParam)
            })
        }
    }

    private val viewModel: BookingViewModel by inject()

    private var price: Long = 0L
    private var bookingParam: BookingParam? = null
    private var selectedPaymentMethod: Bank? = null
    private var selectedVoucher: Voucher? = null

    private val paymentMethodAdapter by lazy {
        PaymentMethodAdapter(this, ::handleSelectedPayment)
    }

    override fun getViewBinding(): ActivityPaymentMethodBinding =
        ActivityPaymentMethodBinding.inflate(layoutInflater)

    override fun initIntent() {
        super.initIntent()

        bookingParam = intent.getParcelableExtra(ConstantKey.KEY_BOOKING_PARAM)
        price = intent.getLongExtra(ConstantKey.KEY_PRICE, 0L)
    }

    override fun initUI() = with(binding) {
        tvPrice.text = price.toCurrency()
        initPaymentMethod()
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        voucherContainer.onClick {
            if (selectedPaymentMethod != null) {
                showVoucher()
            }
        }
        btnPay.onClick { handlePayment() }
    }

    override fun initProcess() {
        paymentMethodAdapter.setData = BankType.entries.map { Selectable(it.bank.toDomain()) }
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.booking.observerLiveData(
            this,
            onLoading = ::showLoading,
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = {
                hideLoading()
                PaymentMethodDetailActivity.start(this, it.bookingId ?: 0)
            }
        )
    }

    private fun handleSelectedPayment(bank: Bank) = with(binding) {
        tvVoucherCode.text = ""
        tvPrice.text = price.toCurrency()
        selectedPaymentMethod = bank
        btnPay.enable()
    }

    private fun handlePayment() {
        val body = bookingParam?.copy(
            paymentMethod = selectedPaymentMethod?.code,
            voucherId = selectedVoucher?.id
        ) ?: return
        viewModel.createBooking(body)
    }

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
        VoucherBottomSheetFragment.newInstance(
            price = price,
            paymentMethod = selectedPaymentMethod?.code.toString()
        ) { voucher ->
            selectedVoucher = voucher
            binding.tvVoucherCode.text = voucher.code
            binding.tvPrice.text = (price - voucher.maximumDiscount).toCurrency()
        }.show(supportFragmentManager, ConstantTag.TAG_VOUCHER)
    }
}
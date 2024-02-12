package com.synrgy.kaboor.payment

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.ext.toCurrency
import com.synrgy.common.utils.ext.toDayMonthFormat
import com.synrgy.common.utils.ext.toFullDateFormat
import com.synrgy.common.utils.ext.toHourMinuteFormat
import com.synrgy.common.utils.ext.toTimeZoneFormat
import com.synrgy.domain.booking.model.response.BookingStatus
import com.synrgy.kaboor.databinding.ActivityPaymentStatusBinding
import com.wahidabd.library.utils.exts.observerLiveData
import org.koin.android.ext.android.inject

class PaymentStatusActivity : KaboorActivity<ActivityPaymentStatusBinding>() {

    companion object {
        fun start(
            context: Context,
            id: Int
        ) {
            context.startActivity(Intent(context, PaymentStatusActivity::class.java).apply {
                putExtra(ConstantKey.KEY_BOOKING_ID, id)
            })
        }
    }

    private val viewModel: PaymentViewModel by inject()

    private var id = 0

    override fun initIntent() {
        super.initIntent()

        id = intent.getIntExtra(ConstantKey.KEY_BOOKING_ID, 0)
    }

    override fun getViewBinding(): ActivityPaymentStatusBinding {
        return ActivityPaymentStatusBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initProcess() {
        super.initProcess()
        viewModel.getPaymentStatus(id)
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.status.observerLiveData(
            this,
            onLoading = ::showLoading,
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = {
                hideLoading()
                setupView(it)
            }
        )
    }

    private fun setupView(data: BookingStatus) = with(binding){
        tvBookingCode.text = data.invoiceNumber
        tvPayment.text = data.methodName
        tvDate.text = data.expiredTime?.toTimeZoneFormat()?.toDayMonthFormat()
        tvDateTime.text = data.expiredTime?.toTimeZoneFormat()?.toHourMinuteFormat()
        tvTotalPayment.text = data.totalPrice?.toCurrency()
    }
}
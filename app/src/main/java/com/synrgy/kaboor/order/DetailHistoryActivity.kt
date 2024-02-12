package com.synrgy.kaboor.order

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.enums.ClipboardType
import com.synrgy.common.utils.enums.PassengerClassType
import com.synrgy.common.utils.enums.PlaneClassType
import com.synrgy.common.utils.ext.convertToDuration
import com.synrgy.common.utils.ext.copyTextToClipboard
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.openPDFContent
import com.synrgy.common.utils.ext.toFullDateFormat
import com.synrgy.common.utils.ext.toGmtFormat
import com.synrgy.common.utils.ext.toStringTrim
import com.synrgy.domain.order.model.response.TicketDetail
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityDetailHistoryBinding
import com.synrgy.kaboor.order.adapter.PassengerHistoryAdapter
import com.synrgy.kaboor.payment.PaymentStatusActivity
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import org.koin.android.ext.android.inject

class DetailHistoryActivity : KaboorActivity<ActivityDetailHistoryBinding>() {

    companion object {
        fun start(
            context: Context,
            key: Pair<Int, String>,
        ) {
            context.startActivity(Intent(context, DetailHistoryActivity::class.java).apply {
                putExtra(ConstantKey.KEY_BOOKING_ID, key.first)
                putExtra(ConstantKey.KEY_TYPE, key.second)
            })
        }
    }

    private val viewModel: OrderViewModel by inject()

    private var id = 0
    private var type = emptyString()

    private val passengerAdapter by lazy {
        PassengerHistoryAdapter(this)
    }

    override fun initIntent() {
        super.initIntent()

        id = intent.getIntExtra(ConstantKey.KEY_BOOKING_ID, 0)
        type = intent.getStringExtra(ConstantKey.KEY_TYPE) ?: emptyString()
    }

    override fun getViewBinding(): ActivityDetailHistoryBinding {
        return ActivityDetailHistoryBinding.inflate(layoutInflater)
    }

    override fun initUI() = with(binding) {
        appbar.setTitle("ID : $id")
        rvPassenger.adapter = passengerAdapter
    }

    override fun initAction() = with(binding) {
        super.initAction()

        appbar.setOnBackClickListener { onBackPress() }
        imgCopy.onClick {
            copyTextToClipboard(
                tvBookingCode.toStringTrim(),
                ClipboardType.BOOKING_CODE
            )
        }

        btnStatus.onClick {
            PaymentStatusActivity.start(
                this@DetailHistoryActivity,
                Pair(id, type)
            )
        }

        btnTicket.onClick { viewModel.downloadTicket(id, type) }
    }

    override fun initProcess() {
        super.initProcess()

        if (type == "outbound") {
            viewModel.getOutbound(id)
        } else {
            viewModel.getReturn(id)
        }
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.ticket.observerLiveData(
            this,
            onLoading = ::showLoading,
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = { ticket ->
                hideLoading()
                setView(ticket)
            }
        )

        viewModel.download.observerLiveData(
            this,
            onLoading = ::showLoading,
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = { body ->
                hideLoading()
                openPDFContent(id, body.byteStream(), ::openPdfReader)
            }
        )
    }

    private fun openPdfReader(intent: Intent) {
        this.startActivity(Intent.createChooser(intent, "Open PDF"))
    }

    private fun setView(data: TicketDetail) = with(binding) {
        val flight = data.flight
        val plane = flight.plane
        val origin = flight.originAirport
        val destination = flight.destinationAirport

        tvBookingCode.text = data.bookingCode
        tvAirline.text = getString(R.string.format_string, plane.airline?.name, plane.code)
        tvOrigin.text = origin.code
        tvDestination.text = destination.code
        tvTakeOff.text = origin.timezone.toGmtFormat(flight.departureDateTime)
        tvLanding.text = destination.timezone.toGmtFormat(flight.arrivalDateTime)
        tvDuration.text = convertToDuration(flight.departureDateTime, flight.arrivalDateTime)
        tvDateTime.text = flight.departureDateTime.toFullDateFormat()
        imgAirline.setImageUrl(this@DetailHistoryActivity, plane.airline?.imageUrl ?: emptyString())
        tvClass.text = PlaneClassType.getByCode(data.classCode).label
        imgTravel.isSelected = data.addTravelInsurance
        imgBaggage.isSelected = data.addBaggage
        imgDelay.isSelected = data.addDelayProtection

        passengerAdapter.setExtraBaggage(data.addBaggage)
        passengerAdapter.setData = data.passengers
    }
}
package com.synrgy.kaboor.notification

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.toCurrency
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.domain.notification.mapper.toFlightParam
import com.synrgy.domain.notification.model.response.PriceNotification
import com.synrgy.kaboor.R
import com.synrgy.kaboor.booking.adapter.PlaneTicketAdapter
import com.synrgy.kaboor.booking.viewmodel.FlightViewModel
import com.synrgy.kaboor.databinding.ActivityNotificationDetailBinding
import com.wahidabd.library.utils.exts.observerLiveData
import org.koin.android.ext.android.inject

class NotificationDetailActivity : KaboorActivity<ActivityNotificationDetailBinding>() {

    companion object {
        fun start(context: Context, notification: PriceNotification) {
            context.startActivity(Intent(context, NotificationDetailActivity::class.java).apply {
                putExtra(ConstantKey.KEY_NOTIFICATION, notification)
            })
        }
    }

    private val viewModel: FlightViewModel by inject()
    private var notification: PriceNotification? = null
    private var flightParam: FlightParam? = null

    private val flightAdapter by lazy {
        PlaneTicketAdapter(this, notification?.toFlightParam())
    }

    override fun getViewBinding(): ActivityNotificationDetailBinding {
        return ActivityNotificationDetailBinding.inflate(layoutInflater)
    }

    override fun initIntent() {
        super.initIntent()
        notification = intent.getParcelableExtra(ConstantKey.KEY_NOTIFICATION)
    }

    override fun initUI() = with(binding) {
        val layoutManager =
            LinearLayoutManager(
                this@NotificationDetailActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        rvTicket.layoutManager = layoutManager
        rvTicket.adapter = flightAdapter

        with(item) {
            tvOrigin.text = notification?.originAirport?.code
            tvDestination.text = notification?.destinationAirport?.code
            tvDate.text = notification?.date
            tvPassenger.text = getString(R.string.format_people, notification?.countPassenger())
            tvSeat.text = notification?.classCode
            tvPrice.text =
                "${notification?.minimumPrice?.toCurrency()} - ${notification?.maximumPrice?.toCurrency()}"
        }

        flightParam = FlightParam(
            originCity = notification?.originAirport?.code.toString(),
            destinationCity = notification?.destinationAirport?.code.toString(),
            numOfKids = notification?.totalChildren,
            numOfBabies = notification?.totalBabies,
            numOfAdults = notification?.totalAdults,
            classCode = notification?.classCode.toString(),
            departureDate = notification?.date.toString(),
            minimumPrice = notification?.minimumPrice,
            maximumPrice = notification?.maximumPrice,
        )
    }

    override fun initAction() {
        super.initAction()
        binding.appbar.setOnBackClickListener { onBackPress() }
    }

    override fun initProcess() {
        super.initProcess()

        flightParam?.let { viewModel.getFlight(it) }
    }

    override fun initObservers() {
        viewModel.flights.observerLiveData(
            this,
            onLoading = ::showLoading,
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = { flights ->
                hideLoading()
                flightAdapter.setData = flights
            }
        )
    }
}
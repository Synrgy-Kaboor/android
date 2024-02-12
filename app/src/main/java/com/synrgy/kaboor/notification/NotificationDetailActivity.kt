package com.synrgy.kaboor.notification

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.toCurrency
import com.synrgy.domain.notification.mapper.toFlightParam
import com.synrgy.domain.notification.model.response.PriceNotification
import com.synrgy.kaboor.R
import com.synrgy.kaboor.booking.adapter.PlaneTicketAdapter
import com.synrgy.kaboor.databinding.ActivityNotificationDetailBinding

class NotificationDetailActivity : KaboorActivity<ActivityNotificationDetailBinding>() {

    companion object {
        fun start(context: Context, notification: PriceNotification) {
            context.startActivity(Intent(context, NotificationDetailActivity::class.java).apply {
                putExtra(ConstantKey.KEY_NOTIFICATION, notification)
            })
        }
    }

    private var notification: PriceNotification? = null

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

        rvTicket.adapter = flightAdapter

        with(item) {
            tvOrigin.text = notification?.originAirport?.name
            tvDestination.text = notification?.destinationAirport?.name
            tvDate.text = notification?.date
            tvPassenger.text = getString(R.string.format_people, notification?.countPassenger())
            tvSeat.text = notification?.classCode
            tvPrice.text =
                "${notification?.minimumPrice?.toCurrency()} - ${notification?.maximumPrice?.toCurrency()}"
        }
    }

    override fun initAction() {
        super.initAction()
        binding.appbar.setOnBackClickListener { onBackPress() }
    }

    override fun initProcess() {
        super.initProcess()

//        flightAdapter.setData = ConstantDummy.planeFlight()
    }


}
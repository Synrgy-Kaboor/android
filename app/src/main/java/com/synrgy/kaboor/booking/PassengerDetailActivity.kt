package com.synrgy.kaboor.booking

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.constant.ConstantTag
import com.synrgy.common.utils.enums.DetailPassengerType
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.domain.booking.model.response.Passenger
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.domain.flight.model.response.Flight
import com.synrgy.kaboor.R
import com.synrgy.kaboor.booking.adapter.PassengerAdapter
import com.synrgy.kaboor.booking.adapter.PlaneTicketAdapter
import com.synrgy.kaboor.booking.dialog.DetailPassengerBottomSheetFragment
import com.synrgy.kaboor.booking.viewmodel.PassengerViewModel
import com.synrgy.kaboor.databinding.ActivityPassengerDetailBinding
import com.wahidabd.library.utils.exts.onClick
import org.koin.android.ext.android.inject

class PassengerDetailActivity : KaboorActivity<ActivityPassengerDetailBinding>() {

    companion object {
        fun start(
            context: Context,
            departureFlightParam: Flight?,
            returnFlightParam: Flight? = null,
            flightParam: FlightParam?
        ) {
            context.startActivity(Intent(context, PassengerDetailActivity::class.java).apply {
                putExtra(ConstantKey.KEY_DEPARTURE_FLIGHT, departureFlightParam)
                putExtra(ConstantKey.KEY_RETURN_FLIGHT, returnFlightParam)
                putExtra(ConstantKey.KEY_FLIGHT_PARAM, flightParam)
            })
        }
    }

    private val passengerViewModel: PassengerViewModel by inject()

    private var flightParam: FlightParam? = null
    private var departureFlight: Flight? = null
    private var returnFlight: Flight? = null
    private var listFlight: MutableList<Flight> = mutableListOf()

    private var passenger = Passenger()
    private var booker = Passenger()
    private var listPassenger = mutableListOf<Passenger>()

    private val planeTicketAdapter by lazy {
        PlaneTicketAdapter(this, flightParam)
    }

    private val passengerAdapter by lazy {
        PassengerAdapter(this) {
            showDetailInfo(DetailPassengerType.PASSENGER, it)
        }
    }

    override fun getViewBinding(): ActivityPassengerDetailBinding =
        ActivityPassengerDetailBinding.inflate(layoutInflater)

    override fun initUI() {
        initAdapter()
    }

    override fun initIntent() {
        departureFlight = intent.getParcelableExtra(ConstantKey.KEY_DEPARTURE_FLIGHT)
        returnFlight = intent.getParcelableExtra(ConstantKey.KEY_RETURN_FLIGHT)
        flightParam = intent.getParcelableExtra(ConstantKey.KEY_FLIGHT_PARAM)

        departureFlight?.let { listFlight.add(it) }
        returnFlight?.let { listFlight.add(it) }
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        cvBookerDetail.onClick { showDetailInfo(DetailPassengerType.BOOKER) }
        ivEdit.onClick { if (!btnSwitch.isChecked) showDetailInfo(DetailPassengerType.PASSENGER_BOOKER) }
        btnOrder.onClick { ExtraProtectionActivity.start(this@PassengerDetailActivity) }
    }

    override fun initProcess() {
        passengerViewModel.getUser()
        planeTicketAdapter.setData = listFlight

        for (i in 1 until flightParam?.countPassenger()!!) {
            listPassenger.add(Passenger())
        }

        passengerAdapter.setData = listPassenger
    }

    override fun initObservers() {
        super.initObservers()

        passengerViewModel.user.observe(this) {
            with(binding) {
                tvBookerName.text = getString(R.string.format_title_name, it.title, it.fullName)
                tvPhone.text = it.phoneNumber
                tvEmail.text = it.email
            }
        }
    }

    private fun showDetailInfo(type: DetailPassengerType, position: Int = -1) {
        DetailPassengerBottomSheetFragment.newInstance(type) {
            when (type) {
                DetailPassengerType.PASSENGER -> {
                    listPassenger[position] = it
                    passengerAdapter.notifyItemChanged(position)
                }
                DetailPassengerType.BOOKER -> {
                    booker = it
                    binding.tvBookerName.text = getString(R.string.format_title_name, it.title, it.fullName)
                }
                DetailPassengerType.PASSENGER_BOOKER -> {
                    passenger = it
                    binding.tvPassengerName.text = getString(R.string.format_title_name, it.title, it.fullName)
                }
            }
        }.show(supportFragmentManager, ConstantTag.TAG_DETAIL_PASSENGER)
    }

    private fun initAdapter() = with(binding) {
        val layoutManager =
            LinearLayoutManager(
                this@PassengerDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        rvTicket.layoutManager = layoutManager
        rvTicket.adapter = planeTicketAdapter

        rvPassenger.adapter = passengerAdapter
    }
}
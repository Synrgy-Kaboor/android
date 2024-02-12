package com.synrgy.kaboor.booking

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.constant.ConstantTag
import com.synrgy.common.utils.enums.DetailPassengerType
import com.synrgy.common.utils.ext.calculatePlanePrice
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.snackbarDanger
import com.synrgy.domain.booking.mapper.toParam
import com.synrgy.domain.booking.model.request.BookingParam
import com.synrgy.domain.booking.model.response.Passenger
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.domain.flight.model.response.Flight
import com.synrgy.domain.user.mapper.toPassenger
import com.synrgy.kaboor.R
import com.synrgy.kaboor.booking.adapter.PassengerAdapter
import com.synrgy.kaboor.booking.adapter.PlaneTicketAdapter
import com.synrgy.kaboor.booking.dialog.DetailPassengerBottomSheetFragment
import com.synrgy.kaboor.booking.viewmodel.PassengerViewModel
import com.synrgy.kaboor.databinding.ActivityPassengerDetailBinding
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.orZero
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
        ivEdit.onClick { showDetailInfo(DetailPassengerType.PASSENGER_BOOKER) }
        btnSwitch.setOnCheckedChangeListener { _, checked ->
            if (checked) tvPassengerName.text =
                getString(R.string.format_title_name, passenger.title, passenger.fullName)
            else tvPassengerName.text = emptyString()
        }
        btnOrder.onClick { handleOrder() }
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
                passenger = it.toPassenger()
                booker = it.toPassenger()
                tvBookerName.text = getString(R.string.format_title_name, it.title, it.fullName)
                tvPhone.text = it.phoneNumber
                tvEmail.text = it.email
            }
        }
    }

    private fun handleOrder() {
        if (!validate()) {
            snackbarDanger(getString(R.string.message_empty_passenger_data))
            return
        }

        val passengers = mutableListOf<Passenger>().apply {
            add(passenger)
            addAll(listPassenger)
        }
        val body = BookingParam(
            outboundFlightId = departureFlight?.id ?: 0,
            returnFlightId = returnFlight?.id,
            classCode = flightParam?.classCode.toString(),
            totalAdult = flightParam?.numOfAdults ?: 0,
            totalChild = flightParam?.numOfKids ?: 0,
            totalBaby = flightParam?.numOfBabies ?: 0,
            orderer = booker.toParam(),
            passengers = passengers.map { it.toParam() },
            addBaggage = binding.baggage.isSelectedItem,
        )
        val price = calculatePrice()
        ExtraProtectionActivity.start(this, body, price)
    }

    private fun showDetailInfo(type: DetailPassengerType, position: Int = -1) {
        DetailPassengerBottomSheetFragment.newInstance(
            type,
            when (type) {
                DetailPassengerType.PASSENGER -> listPassenger[position]
                DetailPassengerType.BOOKER -> booker
                DetailPassengerType.PASSENGER_BOOKER -> passenger
            }
        ) {
            when (type) {
                DetailPassengerType.PASSENGER -> {
                    listPassenger[position] = it
                    passengerAdapter.notifyItemChanged(position)
                }

                DetailPassengerType.BOOKER -> {
                    booker = it
                    binding.tvBookerName.text =
                        getString(R.string.format_title_name, it.title, it.fullName)
                    binding.tvPhone.text = it.phoneNumber

                }

                DetailPassengerType.PASSENGER_BOOKER -> {
                    passenger = it
                    binding.tvPassengerName.text =
                        getString(R.string.format_title_name, it.title, it.fullName)
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

    private fun validate(): Boolean {
        val hasEmptyListPassenger =
            listPassenger.any { it.fullName.isNullOrEmpty() || it.title.isNullOrEmpty() }
        val hasEmptyBooker =
            booker.fullName.isNullOrEmpty() || booker.title.isNullOrEmpty() || booker.phoneNumber.isNullOrEmpty() || booker.email.isNullOrEmpty()
        val hasEmptyPassenger =
            passenger.fullName.isNullOrEmpty() || passenger.title.isNullOrEmpty()

        return !hasEmptyListPassenger && !hasEmptyBooker && !hasEmptyPassenger
    }

    private fun calculatePrice(): Long {
        var price =  calculatePlanePrice(
            Pair(departureFlight?.adultPrice.orZero(), flightParam?.numOfAdults.orZero()),
            Pair(departureFlight?.childPrice.orZero(), flightParam?.numOfKids.orZero()),
            Pair(departureFlight?.babyPrice.orZero(), flightParam?.numOfBabies.orZero()),
            Pair(returnFlight?.adultPrice.orZero(), flightParam?.numOfAdults.orZero()),
            Pair(returnFlight?.childPrice.orZero(), flightParam?.numOfKids.orZero()),
            Pair(returnFlight?.babyPrice.orZero(), flightParam?.numOfBabies.orZero())
        )

        if (binding.baggage.isSelectedItem) {
            price += (flightParam?.countPassenger()?.times(binding.baggage.price) ?: 0).toLong()
        }

        return price
    }
}
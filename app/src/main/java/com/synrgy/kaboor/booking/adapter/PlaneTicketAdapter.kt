package com.synrgy.kaboor.booking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.common.utils.enums.PlaneClassType
import com.synrgy.common.utils.ext.convertToDuration
import com.synrgy.common.utils.ext.toCurrency
import com.synrgy.common.utils.ext.toGmtFormat
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.domain.flight.model.response.Flight
import com.synrgy.kaboor.databinding.ItemCardTicketBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.orZero
import com.wahidabd.library.utils.exts.setImageUrl

class PlaneTicketAdapter(
    private val context: Context,
    private val param: FlightParam?,
    private val onClick: ((Flight) -> Unit)? = null,
) : BaseAsyncRecyclerAdapter<Flight, PlaneTicketAdapter.PlaneTicketViewHolder>() {
    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        ItemCardTicketBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PlaneTicketViewHolder =
        PlaneTicketViewHolder(getViewBinding(parent, viewType))

    inner class PlaneTicketViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<Flight>(binding) {
        override fun bind(data: Flight) = with(binding as ItemCardTicketBinding) {
            imgPlane.setImageUrl(context, data.plane.airline?.imageUrl.toString())
            tvPlane.text = data.plane.airline?.name
            tvOrigin.text = data.originAirport.code
            tvDestination.text = data.destinationAirport.code
            tvTakeOff.text = data.originAirport.timezone.toGmtFormat(data.departureDatetime)
            tvDuration.text = convertToDuration(data.departureDatetime, data.arrivalDatetime)
            tvLanding.text = data.destinationAirport.timezone.toGmtFormat(data.arrivalDatetime)
            tvClass.text = PlaneClassType.getByCode(param?.classCode).label

            val price = (data.adultPrice * param?.numOfAdults.orZero()) +
                    (data.childPrice * param?.numOfKids.orZero()) +
                    (data.babyPrice * param?.numOfBabies.orZero())
            tvPrice.text = price.toCurrency()

//            if (data.date != "") {
//                tvDate.visibility = View.VISIBLE
//                tvDate.text = data.date
//            }

            root.onClick {
                onClick?.invoke(data)
            }
        }
    }
}
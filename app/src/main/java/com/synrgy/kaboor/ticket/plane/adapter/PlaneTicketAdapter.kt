package com.synrgy.kaboor.ticket.plane.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.common.R
import com.synrgy.domain.ticket.plane.Ticket
import com.synrgy.kaboor.databinding.ItemCardTicketBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick

class PlaneTicketAdapter(
    private val context: Context,
    private val onClick: (Ticket) -> Unit,
) : BaseAsyncRecyclerAdapter<Ticket, PlaneTicketAdapter.PlaneTicketViewHolder>() {
    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        ItemCardTicketBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PlaneTicketAdapter.PlaneTicketViewHolder =
        PlaneTicketViewHolder(getViewBinding(parent, viewType))

    inner class PlaneTicketViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<Ticket>(binding) {
        override fun bind(data: Ticket) = with(binding as ItemCardTicketBinding) {
            imgPlane.setImageResource(data.image ?: R.drawable.sample_img_garuda)
            tvPlane.text = data.plane
            tvClass.text = data.typeClass
            tvOrigin.text = data.departure
            tvTakeOff.text = data.departureTime
            tvDuration.text = data.boardingTime
            tvDestination.text = data.destination
            tvLanding.text = data.destinationTime
            tvPrice.text = data.price.toString()

            root.onClick {
                onClick.invoke(data)
            }
        }
    }
}
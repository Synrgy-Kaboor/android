package com.synrgy.kaboor.order.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.common.R
import com.synrgy.domain.flight.model.response.OrderFlight
import com.synrgy.kaboor.databinding.ItemCardOrderHistoryBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder

class OrderAdapter(
    private val context: Context,
) : BaseAsyncRecyclerAdapter<OrderFlight, OrderAdapter.OrderViewHolder>() {
    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        ItemCardOrderHistoryBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OrderViewHolder =
        OrderViewHolder(getViewBinding(parent, viewType))

    inner class OrderViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<OrderFlight>(binding) {
        override fun bind(data: OrderFlight) = with(binding as ItemCardOrderHistoryBinding) {
            imgPlane.setImageResource(data.image ?: R.drawable.sample_img_garuda)
            tvOrderId.text = context.getString(R.string.format_order_id, data.id)
            tvOrigin.text = data.departure
            tvDestination.text = data.destination
            tvDate.text = data.boardingDate
            tvTime.text = data.boardingTime
            tvOrderStatus.text = data.orderStatus
        }
    }
}
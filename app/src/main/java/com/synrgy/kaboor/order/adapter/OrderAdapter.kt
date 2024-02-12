package com.synrgy.kaboor.order.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.common.R
import com.synrgy.common.utils.enums.OrderType
import com.synrgy.common.utils.ext.toFullDateFormat
import com.synrgy.common.utils.ext.toGmtFormat
import com.synrgy.domain.order.model.response.Order
import com.synrgy.kaboor.databinding.ItemCardOrderHistoryBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.getCompatDrawable
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl

class OrderAdapter(
    private val context: Context,
    private val isCompleted: Boolean = false,
    private val listener: (Pair<Int, String>, OrderType) -> Unit,
) : BaseAsyncRecyclerAdapter<Order, OrderAdapter.OrderViewHolder>() {
    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        ItemCardOrderHistoryBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OrderViewHolder =
        OrderViewHolder(getViewBinding(parent, viewType))

    inner class OrderViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<Order>(binding) {
        override fun bind(data: Order) = with(binding as ItemCardOrderHistoryBinding) {
            imgPlane.setImageUrl(context, data.flight.plane.airline?.imageUrl.toString())
            tvOrderId.text = context.getString(R.string.format_order_id, data.bookingId)
            tvOrigin.text = data.flight.originAirport.code
            tvDestination.text = data.flight.destinationAirport.code
            tvDate.text = data.flight.departureDateTime.toFullDateFormat()
            tvTime.text = data.flight.originAirport.timezone.toGmtFormat(data.flight.departureDateTime)

            val order = OrderType.getBackground(
                Pair(data.uploadedProofOfPayment, data.paymentCompleted),
                isCompleted
            )
            tvOrderStatus.text = order.label
            tvOrderStatus.background = context.getCompatDrawable(order.background)

            root.onClick {
                listener.invoke(Pair(data.id, data.type), order)
            }
        }
    }
}
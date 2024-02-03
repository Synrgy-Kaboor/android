package com.synrgy.kaboor.notification.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.common.utils.ext.toCurrency
import com.synrgy.domain.notification.model.response.PriceNotification
import com.synrgy.kaboor.databinding.ItemNotificationPriceBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 2/2/2024.
 * Github github.com/wahidabd.
 */


class PriceNotificationAdapter(
    private val context: Context,
    private val onMoreClick: (PriceNotification) -> Unit,
    private val onItemClick: (PriceNotification) -> Unit
) : BaseAsyncRecyclerAdapter<PriceNotification, PriceNotificationAdapter.PriceNotificationViewHolder>() {
    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemNotificationPriceBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PriceNotificationAdapter.PriceNotificationViewHolder {
        return PriceNotificationViewHolder(getViewBinding(parent, viewType))
    }

    inner class PriceNotificationViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<PriceNotification>(binding) {
        override fun bind(data: PriceNotification) = with(binding as ItemNotificationPriceBinding) {
            tvOrigin.text = data.originCity.name
            tvDestination.text = data.destinationCity.name
            tvDate.text = data.departureDate
            tvPrice.text = "${data.lowerPriceLimit?.toCurrency()} - ${data.upperPriceLimit?.toCurrency()}"
            tvPassenger.text = data.countPassenger().toString()
            tvSeat.text = data.clazz
            imgMore.onClick { onMoreClick(data) }
            container.onClick { onItemClick(data) }
        }
    }
}
package com.synrgy.kaboor.order.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.common.utils.ext.goneIf
import com.synrgy.domain.booking.model.response.Passenger
import com.synrgy.kaboor.databinding.ItemPassengerHistoryBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder


/**
 * Created by wahid on 2/10/2024.
 * Github github.com/wahidabd.
 */


class PassengerHistoryAdapter(
    private val context: Context,
) : BaseAsyncRecyclerAdapter<Passenger, PassengerHistoryAdapter.PassengerHistoryViewHolder>() {

    private var extraBaggage = false

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemPassengerHistoryBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PassengerHistoryAdapter.PassengerHistoryViewHolder {
        return PassengerHistoryViewHolder(getViewBinding(parent, viewType))
    }

    inner class PassengerHistoryViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<Passenger>(binding) {
        override fun bind(data: Passenger) = with(binding as ItemPassengerHistoryBinding) {
            tvIndex.text = bindingAdapterPosition.toString()
            tvName.text = "${data.title} ${data.fullName}"
            tvExtraBaggage.goneIf { !extraBaggage }
        }
    }

    fun setExtraBaggage(extraBaggage: Boolean) {
        this.extraBaggage = extraBaggage
    }
}
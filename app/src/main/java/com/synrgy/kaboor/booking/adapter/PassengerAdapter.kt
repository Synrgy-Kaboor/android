package com.synrgy.kaboor.booking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.domain.booking.model.response.Passenger
import com.synrgy.kaboor.databinding.ItemPassengerBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 2/3/2024.
 * Github github.com/wahidabd.
 */


class PassengerAdapter(
    private val context: Context,
    private val onEditClick: (Int) -> Unit,
) : BaseAsyncRecyclerAdapter<Passenger, PassengerAdapter.PassengerViewHolder>() {
    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemPassengerBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PassengerAdapter.PassengerViewHolder {
        return PassengerViewHolder(getViewBinding(parent, viewType))
    }

    inner class PassengerViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<Passenger>(binding) {
        override fun bind(data: Passenger) = with(binding as ItemPassengerBinding) {
            tvName.text = data.fullName

            ivEdit.onClick { onEditClick.invoke(bindingAdapterPosition) }
        }
    }
}
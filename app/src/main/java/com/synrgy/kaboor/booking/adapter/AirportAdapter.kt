package com.synrgy.kaboor.booking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.common.model.AirportData
import com.synrgy.common.R
import com.synrgy.kaboor.databinding.ItemAirportBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 1/6/2024.
 * Github github.com/wahidabd.
 */


class AirportAdapter(
    private val context: Context,
    private val onItemClick: (AirportData) -> Unit
) : BaseAsyncRecyclerAdapter<AirportData, AirportAdapter.AirportViewHolder>() {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        ItemAirportBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AirportViewHolder = AirportViewHolder(getViewBinding(parent, viewType))

    inner class AirportViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<AirportData>(binding) {
        override fun bind(data: AirportData) = with(binding as ItemAirportBinding) {
            tvCode.text = data.code
            tvName.text = data.name

            root.onClick {
                onItemClick.invoke(data)
            }
        }
    }

    fun filter(query: ArrayList<AirportData>) {
        setData = query
    }
}
package com.synrgy.kaboor.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.kaboor.databinding.ItemPromoImageBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder


/**
 * Created by wahid on 12/31/2023.
 * Github github.com/wahidabd.
 */


class PromoAdapter(
    private val context: Context,
) : BaseAsyncRecyclerAdapter<Int, PromoAdapter.PromoViewHolder>() {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        ItemPromoImageBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PromoAdapter.PromoViewHolder = PromoViewHolder(getViewBinding(parent, viewType))

    inner class PromoViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<Int>(binding) {
        override fun bind(data: Int) = with(binding as ItemPromoImageBinding) {
            imgPromo.setImageResource(data)
        }
    }
}
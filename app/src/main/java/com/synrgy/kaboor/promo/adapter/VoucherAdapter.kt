package com.synrgy.kaboor.promo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.data.payment.model.response.Promo
import com.synrgy.kaboor.databinding.ItemVoucherBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder


/**
 * Created by wahid on 1/21/2024.
 * Github github.com/wahidabd.
 */


class VoucherAdapter(
    private val context: Context,
) : BaseAsyncRecyclerAdapter<Promo, VoucherAdapter.VoucherViewHolder>() {
    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemVoucherBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VoucherAdapter.VoucherViewHolder {
        return VoucherViewHolder(getViewBinding(parent, viewType))
    }

    inner class VoucherViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<Promo>(binding) {
        override fun bind(data: Promo) = with(binding as ItemVoucherBinding) {
            tvVoucherType.text = data.type
            tvTitle.text = data.title
            tvDescription.text = data.description
            tvSave.text = data.saveUpTo.toString()
            tvCode.text = data.voucherCode.uppercase()
            tvEndDate.text = data.voucherEnded.toString()
        }
    }
}
package com.synrgy.kaboor.promo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.common.utils.ext.toCurrency
import com.synrgy.common.utils.ext.toPromoDate
import com.synrgy.domain.promo.model.response.Voucher
import com.synrgy.kaboor.databinding.ItemVoucherBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder


/**
 * Created by wahid on 1/21/2024.
 * Github github.com/wahidabd.
 */


class VoucherAdapter(
    private val context: Context,
) : BaseAsyncRecyclerAdapter<Voucher, VoucherAdapter.VoucherViewHolder>() {
    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemVoucherBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VoucherAdapter.VoucherViewHolder {
        return VoucherViewHolder(getViewBinding(parent, viewType))
    }

    inner class VoucherViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<Voucher>(binding) {
        override fun bind(data: Voucher) = with(binding as ItemVoucherBinding) {
            tvTitle.text = data.title
            tvDescription.text = data.description
            tvSave.text = data.maximumDiscount.toCurrency()
            tvCode.text = data.code
            tvEndDate.text = data.expiredTime.toPromoDate()
        }
    }
}
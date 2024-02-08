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
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.disable
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 1/21/2024.
 * Github github.com/wahidabd.
 */


class VoucherAdapter(
    private val context: Context,
    private val eligible: Pair<String, Long>,
    private val onItemClick: (Voucher) -> Unit
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

    inner class VoucherViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<Voucher>(binding) {
        override fun bind(data: Voucher) = with(binding as ItemVoucherBinding) {
            tvTitle.text = data.title
            tvDescription.text = data.description
            tvSave.text = data.maximumDiscount.toCurrency()
            tvCode.text = data.code
            tvEndDate.text = data.expiredTime.toPromoDate()

            val eligible =
                data.eligiblePaymentMethods.contains(eligible.first) && data.maximumDiscount <= eligible.second
            data.isEligible = eligible

            debug { "$data" }

            if (data.isEligible) card.onClick { onItemClick.invoke(data) }
            else setDisableCard()
        }

        private fun setDisableCard() = with(binding as ItemVoucherBinding) {
            headerContainer.disable()
            tvVoucherType.disable()
            imgIcon.disable()
            tvSave.disable()
            tvTitle.disable()
            tvCode.disable()
            tvEndDate.disable()
        }
    }

    fun filter(query: ArrayList<Voucher>) {
        setData = query
    }
}
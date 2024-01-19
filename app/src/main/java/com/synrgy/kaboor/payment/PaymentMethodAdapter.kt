package com.synrgy.kaboor.payment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.common.R
import com.synrgy.common.utils.Selectable
import com.synrgy.domain.payment.Bank
import com.synrgy.kaboor.databinding.ItemPaymentMethodBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick

class PaymentMethodAdapter(
    private val context: Context,
    private val onItemSelectedListener: (Bank) -> Unit,
) : BaseAsyncRecyclerAdapter<Selectable<Bank>, PaymentMethodAdapter.PaymentMethodViewHolder>() {
    var currentSelectedIndex = -1
    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        ItemPaymentMethodBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PaymentMethodAdapter.PaymentMethodViewHolder =
        PaymentMethodViewHolder(getViewBinding(parent, viewType))

    inner class PaymentMethodViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<Selectable<Bank>>(binding) {
        override fun bind(data: Selectable<Bank>) = with(binding as ItemPaymentMethodBinding) {
            ivBank.setImageResource(data.item.image ?: R.drawable.img_bca)
            tvBankName.text = data.item.bankName

            setSelectedItem(data.selected)

            root.onClick {
                val previousSelectedIndex = currentSelectedIndex
                currentSelectedIndex = bindingAdapterPosition

                if (currentSelectedIndex != previousSelectedIndex) {
                    data.selected = true

                    if (previousSelectedIndex > -1) {
                        setData[previousSelectedIndex].selected = false
                        notifyItemChanged(previousSelectedIndex)
                    }

                    setSelectedItem(data.selected)
                    onItemSelectedListener.invoke(data.item)
                }

            }
        }

        private fun setSelectedItem(isSelected: Boolean) {
            with(binding as ItemPaymentMethodBinding) {
                imgSelectable.isSelected = isSelected
                if (isSelected) currentSelectedIndex = bindingAdapterPosition
            }
        }
    }
}
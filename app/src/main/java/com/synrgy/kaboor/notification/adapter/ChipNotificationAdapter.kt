package com.synrgy.kaboor.notification.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.common.utils.Selectable
import com.synrgy.common.utils.enums.NotificationType
import com.synrgy.kaboor.databinding.ItemChipNotificationBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 2/2/2024.
 * Github github.com/wahidabd.
 */


class ChipNotificationAdapter(
    private val context: Context,
    private val onItemClick: (NotificationType) -> Unit,
) : BaseAsyncRecyclerAdapter<Selectable<NotificationType>, ChipNotificationAdapter.ChipNotificationViewHolder>() {

    var currentSelectedIndex = -1
    var defaultItem = NotificationType.ALL

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemChipNotificationBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChipNotificationAdapter.ChipNotificationViewHolder {
        return ChipNotificationViewHolder(getViewBinding(parent, viewType))
    }

    inner class ChipNotificationViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<Selectable<NotificationType>>(binding) {
        override fun bind(data: Selectable<NotificationType>) =
            with(binding as ItemChipNotificationBinding)
            {
                data.selected = data.item == defaultItem
                setSelectedItem(data.selected)
                tvChip.text = data.item.label

                tvChip.onClick {
                    val previousSelectedIndex = currentSelectedIndex
                    currentSelectedIndex = bindingAdapterPosition

                    if (currentSelectedIndex != previousSelectedIndex) {
                        data.selected = true
                        defaultItem = data.item

                        if (previousSelectedIndex > -1) {
                            setData[previousSelectedIndex].selected = false
                            notifyItemChanged(previousSelectedIndex)
                        }
                    }

                    setSelectedItem(data.selected)
                    onItemClick.invoke(data.item)
                }
            }

        private fun setSelectedItem(isSelected: Boolean) {
            with(binding as ItemChipNotificationBinding) {
                tvChip.isSelected = isSelected
                if (isSelected) currentSelectedIndex = bindingAdapterPosition
            }
        }
    }
}
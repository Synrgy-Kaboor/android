package com.synrgy.kaboor.ticket.plane.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.common.utils.enums.PlaneClassType
import com.synrgy.common.utils.ext.Selectable
import com.synrgy.kaboor.databinding.ItemPlaneClassBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 1/6/2024.
 * Github github.com/wahidabd.
 */


class PlaneClassAdapter(
    private val context: Context,
    private val selectedItem: PlaneClassType,
    private val onItemSelectedListener: (PlaneClassType) -> Unit
) : BaseAsyncRecyclerAdapter<Selectable<PlaneClassType>, PlaneClassAdapter.PlaneClassAdapterViewHolder>() {

    var currentSelectedIndex = -1
    var defaultItem = selectedItem

    fun addAll(data: List<PlaneClassType>) {
        data.map { type ->
            Selectable(type)
        }.toMutableList().let { newData ->
            setData = newData
        }
    }

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        ItemPlaneClassBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaneClassAdapter.PlaneClassAdapterViewHolder =
        PlaneClassAdapterViewHolder(getViewBinding(parent, viewType))

    inner class PlaneClassAdapterViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<Selectable<PlaneClassType>>(binding) {
        override fun bind(data: Selectable<PlaneClassType>) =
            with(binding as ItemPlaneClassBinding) {
                data.selected = data.item == defaultItem
                setSelectedItem(data.selected)
                kaboorPassengerClass.setLabel(data.item.label)
                kaboorPassengerClass.setDescription(data.item.description)

                kaboorPassengerClass.onClick {
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
                    onItemSelectedListener.invoke(data.item)
                }
            }

        private fun setSelectedItem(isSelected: Boolean) {
            with(binding as ItemPlaneClassBinding) {
                kaboorPassengerClass.setSelectedItem(isSelected)
                if (isSelected) currentSelectedIndex = bindingAdapterPosition
            }
        }
    }

}
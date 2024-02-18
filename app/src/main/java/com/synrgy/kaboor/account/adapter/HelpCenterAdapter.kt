package com.synrgy.kaboor.account.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.common.utils.Selectable
import com.synrgy.common.utils.ext.goneIf
import com.synrgy.common.utils.ext.showHideToggle
import com.synrgy.domain.user.model.response.HelpCenter
import com.synrgy.kaboor.databinding.ItemHelpCenterBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 1/28/2024.
 * Github github.com/wahidabd.
 */


class HelpCenterAdapter(
    private val context: Context
) : BaseAsyncRecyclerAdapter<Selectable<HelpCenter>, HelpCenterAdapter.HelpCenterViewHolder>() {

    var currentSelectedIndex = -1

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemHelpCenterBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HelpCenterAdapter.HelpCenterViewHolder {
        return HelpCenterViewHolder(getViewBinding(parent, viewType))
    }

    inner class HelpCenterViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<Selectable<HelpCenter>>(binding) {
        override fun bind(data: Selectable<HelpCenter>) = with(binding as ItemHelpCenterBinding) {
            tvTitle.text = data.item.title
            tvContent.text = data.item.content
            tvContentBottom.text = data.item.contentBottom

            tvContentBottom.goneIf { data.item.contentBottom?.isEmpty() == true }

            if (data.item.contentList != 0) {
                val list = context.resources.getStringArray(data.item.contentList)
                list.forEachIndexed { i, s ->
                    tvContentList.append("${i + 1}. $s\n")
                }
            } else tvContentList.gone()


            setSelected(data.selected)

            imgToggle.onClick {
                val previousSelectedIndex = currentSelectedIndex
                currentSelectedIndex = bindingAdapterPosition

                if (currentSelectedIndex != previousSelectedIndex) {
                    data.selected = true

                    if (previousSelectedIndex > -1) {
                        setData[previousSelectedIndex].selected = false
                        notifyItemChanged(previousSelectedIndex)
                    }

                    setSelected(data.selected)
                }
            }
        }

        private fun setSelected(isSelected: Boolean) {
            with(binding as ItemHelpCenterBinding) {
                imgToggle.isSelected = isSelected
                content.showHideToggle()
                if (isSelected) currentSelectedIndex = bindingAdapterPosition
            }
        }
    }
}
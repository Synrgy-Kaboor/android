package com.synrgy.kaboor.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.common.navigation.MenuHome
import com.synrgy.kaboor.databinding.ItemMenuHomeBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 12/30/2023.
 * Github github.com/wahidabd.
 */


class MenuHomeAdapter(
    private val context: Context,
    private val onItemClicked: (MenuHome) -> Unit
) : BaseAsyncRecyclerAdapter<MenuHome, MenuHomeAdapter.MenuHomeViewHolder>() {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        ItemMenuHomeBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuHomeAdapter.MenuHomeViewHolder =
        MenuHomeViewHolder(getViewBinding(parent, viewType))

    inner class MenuHomeViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<MenuHome>(binding) {
        override fun bind(data: MenuHome) = with(binding as ItemMenuHomeBinding) {
            menu.setLabel(context.getString(data.label))
            menu.setIcon(data.icon)

            menu.setOnIconClick { onItemClicked(data) }
        }
    }

}
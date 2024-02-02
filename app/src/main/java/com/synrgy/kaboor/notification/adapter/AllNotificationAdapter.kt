package com.synrgy.kaboor.notification.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.domain.notification.model.response.Notification
import com.synrgy.kaboor.databinding.ItemNotificationBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder


/**
 * Created by wahid on 2/2/2024.
 * Github github.com/wahidabd.
 */


class AllNotificationAdapter(
    private val context: Context
) : BaseAsyncRecyclerAdapter<Notification, AllNotificationAdapter.AllNotificationViewHolder>() {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemNotificationBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllNotificationAdapter.AllNotificationViewHolder {
        return AllNotificationViewHolder(getViewBinding(parent, viewType))
    }

    inner class AllNotificationViewHolder(
        binding: ViewBinding
    ) : BaseAsyncItemViewHolder<Notification>(binding) {
        override fun bind(data: Notification) = with(binding as ItemNotificationBinding) {
            imgIcon.setImageResource(data.icon)
            tvTitle.text = data.title
            tvDescription.text = data.description
            tvDate.text = data.date
        }
    }
}
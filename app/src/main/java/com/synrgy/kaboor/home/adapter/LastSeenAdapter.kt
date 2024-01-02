package com.synrgy.kaboor.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.domain.home.model.LastSeen
import com.synrgy.kaboor.databinding.ItemLastSeenBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 12/31/2023.
 * Github github.com/wahidabd.
 */


class LastSeenAdapter(
    private val context: Context,
    private val onItemClick: (Int) -> Unit
) : BaseAsyncRecyclerAdapter<LastSeen, LastSeenAdapter.LastSeenViewHolder>() {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        ItemLastSeenBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LastSeenAdapter.LastSeenViewHolder = LastSeenViewHolder(getViewBinding(parent, viewType))

    inner class LastSeenViewHolder(
        binding: ViewBinding
    ) : BaseAsyncItemViewHolder<LastSeen>(binding) {
        override fun bind(data: LastSeen) = with(binding as ItemLastSeenBinding) {
            tvName.text = data.name
            imgPicture.setImageResource(data.image)

            root.onClick {
                onItemClick.invoke(data.id)
            }
        }
    }

}
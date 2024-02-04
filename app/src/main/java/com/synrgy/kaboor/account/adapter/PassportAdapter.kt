package com.synrgy.kaboor.account.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.domain.user.model.response.Passport
import com.synrgy.kaboor.databinding.ItemPassportBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder


/**
 * Created by wahid on 2/1/2024.
 * Github github.com/wahidabd.
 */


class PassportAdapter(
    private val context: Context,
) : BaseAsyncRecyclerAdapter<Passport, PassportAdapter.PassportViewHolder>() {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemPassportBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PassportAdapter.PassportViewHolder {
        return PassportViewHolder(getViewBinding(parent, viewType))
    }

    inner class PassportViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<Passport>(binding) {
        override fun bind(data: Passport) = with(binding as ItemPassportBinding) {
            tvName.text = data.fullName
            tvNumber.text = data.passportNumber
        }
    }
}
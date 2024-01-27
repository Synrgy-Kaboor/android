package com.synrgy.kaboor.account

import android.view.LayoutInflater
import android.view.ViewGroup
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.kaboor.authentication.AuthViewModel
import com.synrgy.kaboor.databinding.FragmentAccountBinding
import com.wahidabd.library.utils.exts.onClick
import org.koin.android.ext.android.inject


class AccountFragment : KaboorFragment<FragmentAccountBinding>() {
    private val viewModel: AuthViewModel by inject()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean,
    ): FragmentAccountBinding =
        FragmentAccountBinding.inflate(layoutInflater, container, attachRoot)

    override fun initUI() {}

    override fun initAction() = with(binding) {
        llLogout.onClick {
            viewModel.clearToken()

        }
    }


}
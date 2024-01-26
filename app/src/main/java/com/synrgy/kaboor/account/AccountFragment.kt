package com.synrgy.kaboor.account

import android.view.LayoutInflater
import android.view.ViewGroup
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.kaboor.R
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

    // TODO: Remove this after API ready
    override fun initUI() = with(binding){
        tvUserName.text = "Andre Hutshon"
        tvProgress.text = getString(R.string.format_personal_information, 16)
        progressHorizontal.progress = 16
        tvEmail.text = "andrehustshon@gmail.com"
        imgProfile.setImageResource(R.drawable.sample_profile)
    }

    override fun initAction() = with(binding) {
        profileContainer.onClick { AccountDetailActivity.start(requireContext()) }
        llLogout.onClick {
            viewModel.clearToken()
        }
    }


}
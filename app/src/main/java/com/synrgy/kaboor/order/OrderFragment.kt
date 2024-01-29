package com.synrgy.kaboor.order

import android.view.LayoutInflater
import android.view.ViewGroup
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.common.utils.ext.showLoginState
import com.synrgy.kaboor.authentication.login.LoginActivity
import com.synrgy.kaboor.databinding.FragmentOrderBinding
import com.synrgy.kaboor.home.SharedViewModel
import org.koin.android.ext.android.inject


class OrderFragment : KaboorFragment<FragmentOrderBinding>() {

    private val sharedViewModel: SharedViewModel by inject()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentOrderBinding = FragmentOrderBinding.inflate(layoutInflater, container, attachRoot)

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {
        super.initProcess()
        sharedViewModel.checkLogin()
    }

    override fun initObservers() {
        super.initObservers()

        sharedViewModel.login.observe(viewLifecycleOwner) { state ->
            if (!state) binding.msv.showLoginState { LoginActivity.start(requireContext()) }
        }
    }

}
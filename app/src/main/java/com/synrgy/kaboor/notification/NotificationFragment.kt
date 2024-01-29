package com.synrgy.kaboor.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.common.utils.ext.showLoginState
import com.synrgy.kaboor.authentication.login.LoginActivity
import com.synrgy.kaboor.databinding.FragmentNotificationBinding
import com.synrgy.kaboor.home.SharedViewModel
import org.koin.android.ext.android.inject


class NotificationFragment : KaboorFragment<FragmentNotificationBinding>() {

    private val sharedViewModel: SharedViewModel by inject()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentNotificationBinding =
        FragmentNotificationBinding.inflate(layoutInflater, container, attachRoot)

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
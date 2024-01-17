package com.synrgy.kaboor.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.common.utils.ext.navToHome
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.login.LoginActivity
import com.synrgy.kaboor.databinding.FragmentNotificationBinding


class NotificationFragment : KaboorFragment<FragmentNotificationBinding>() {
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentNotificationBinding =
        FragmentNotificationBinding.inflate(layoutInflater, container, attachRoot)

    override fun initUI() {
//        showAlertLoginDialog(
//            secondaryAction = { navToHome(R.id.homeFragment) },
//            primaryAction = { LoginActivity.start(requireContext()) }
//        )
    }

    override fun initAction() {}

}
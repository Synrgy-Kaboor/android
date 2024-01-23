package com.synrgy.kaboor.order

import android.view.LayoutInflater
import android.view.ViewGroup
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.kaboor.databinding.FragmentOrderBinding


class OrderFragment : KaboorFragment<FragmentOrderBinding>() {
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentOrderBinding = FragmentOrderBinding.inflate(layoutInflater, container, attachRoot)

    override fun initUI() {
//        showAlertLoginDialog(
//            secondaryAction = { navToHome(R.id.homeFragment) },
//            primaryAction = { LoginActivity.start(requireContext()) }
//        )
    }

    override fun initAction() {}


}
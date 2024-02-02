package com.synrgy.kaboor.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.common.utils.Selectable
import com.synrgy.common.utils.enums.NotificationType
import com.synrgy.common.utils.ext.showLoginState
import com.synrgy.kaboor.authentication.login.LoginActivity
import com.synrgy.kaboor.databinding.FragmentNotificationBinding
import com.synrgy.kaboor.home.SharedViewModel
import com.synrgy.kaboor.notification.adapter.AllNotificationAdapter
import com.synrgy.kaboor.notification.adapter.ChipNotificationAdapter
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.visible
import org.koin.android.ext.android.inject


class NotificationFragment : KaboorFragment<FragmentNotificationBinding>() {

    private val sharedViewModel: SharedViewModel by inject()

    private val chipNotificationAdapter by lazy {
        ChipNotificationAdapter(requireContext(), ::initNotificationAdapter)
    }

    private val allNotificationAdapter by lazy {
        AllNotificationAdapter(requireContext())
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentNotificationBinding =
        FragmentNotificationBinding.inflate(layoutInflater, container, attachRoot)

    override fun initUI() {

        initAdapter()
        initNotificationAdapter()
    }

    override fun initAction() {}

    override fun initProcess() {
        super.initProcess()
        sharedViewModel.checkLogin()

        chipNotificationAdapter.setData = NotificationType.entries.map { Selectable(it) }
    }

    override fun initObservers() {
        super.initObservers()

        sharedViewModel.login.observe(viewLifecycleOwner) { state ->
            if (!state) binding.msv.showLoginState { LoginActivity.start(requireContext()) }
        }
    }

    private fun initNotificationAdapter(type: NotificationType = NotificationType.ALL) =
        with(binding) {
            when (type) {
                NotificationType.ALL -> {
                    rvAllNotification.visible()
                    priceContainer.gone()
                    allNotificationAdapter.setData = ConstantDummy.allNotifications()
                }

                NotificationType.PRICE -> {
                    rvAllNotification.gone()
                    priceContainer.visible()
                }
            }
        }

    private fun initAdapter() = with(binding) {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvChip.layoutManager = layoutManager
        rvChip.adapter = chipNotificationAdapter

        rvAllNotification.adapter = allNotificationAdapter
    }
}
package com.synrgy.kaboor.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.common.utils.Selectable
import com.synrgy.common.utils.enums.NotificationType
import com.synrgy.common.utils.enums.PriceAlertType
import com.synrgy.common.utils.ext.showLoginState
import com.synrgy.domain.notification.mapper.toFlightParam
import com.synrgy.domain.notification.model.response.PriceNotification
import com.synrgy.kaboor.authentication.login.LoginActivity
import com.synrgy.kaboor.booking.PriceAlertActivity
import com.synrgy.kaboor.databinding.DialogPriceAlertBinding
import com.synrgy.kaboor.databinding.FragmentNotificationBinding
import com.synrgy.kaboor.home.SharedViewModel
import com.synrgy.kaboor.notification.adapter.AllNotificationAdapter
import com.synrgy.kaboor.notification.adapter.ChipNotificationAdapter
import com.synrgy.kaboor.notification.adapter.PriceNotificationAdapter
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.getCompatDrawable
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible
import org.koin.android.ext.android.inject
import com.synrgy.common.R as comR


class NotificationFragment : KaboorFragment<FragmentNotificationBinding>() {

    private val sharedViewModel: SharedViewModel by inject()
    private val viewModel: NotificationViewModel by inject()

    private val chipNotificationAdapter by lazy {
        ChipNotificationAdapter(requireContext(), ::initNotificationAdapter)
    }

    private val allNotificationAdapter by lazy {
        AllNotificationAdapter(requireContext())
    }

    private val oneWayAdapter by lazy {
        PriceNotificationAdapter(requireContext(), ::showPopUpDialog, ::handleNavigation)
    }

    private val roundTripAdapter by lazy {
        PriceNotificationAdapter(requireContext(), ::showPopUpDialog, ::handleNavigation)
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
        viewModel.getNotification()

        chipNotificationAdapter.setData = NotificationType.entries.map { Selectable(it) }
    }

    override fun initObservers() {
        super.initObservers()

        sharedViewModel.login.observe(viewLifecycleOwner) { state ->
            if (!state) binding.msv.showLoginState { LoginActivity.start(requireContext()) }
        }

        viewModel.notification.observerLiveData(
            viewLifecycleOwner,
            onLoading = ::showLoading,
            onFailure = { _, message ->
                hideLoading()
                showToast(message.toString())
            },
            onSuccess = {
                hideLoading()
                allNotificationAdapter.setData = it
            }
        )
    }

    private fun initNotificationAdapter(type: NotificationType = NotificationType.ALL) =
        with(binding) {
            when (type) {
                NotificationType.ALL -> {
                    rvAllNotification.visible()
                    priceContainer.gone()
                }

                NotificationType.PRICE -> {
                    rvAllNotification.gone()
                    priceContainer.visible()

                    oneWayAdapter.setData = ConstantDummy.priceNotifications()
                    roundTripAdapter.setData = ConstantDummy.priceNotifications()
                }
            }
        }

    private fun initAdapter() = with(binding) {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvChip.layoutManager = layoutManager
        rvChip.adapter = chipNotificationAdapter

        rvAllNotification.adapter = allNotificationAdapter
        rvOneWay.adapter = oneWayAdapter
        rvRoundTrip.adapter = roundTripAdapter
    }

    private fun handleNavigation(data: PriceNotification) {
        NotificationDetailActivity.start(requireContext(), data)
    }

    private fun showPopUpDialog(data: PriceNotification) {
        val dialogBinding = DialogPriceAlertBinding.inflate(LayoutInflater.from(requireContext()))
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(dialogBinding.root)
            .setBackground(requireContext().getCompatDrawable(comR.drawable.bg_stroke_neutral_5))
            .create()

        dialogBinding.edit.onClick {
            PriceAlertActivity.start(
                context = requireContext(),
                flightParam = data.toFlightParam(),
                type = PriceAlertType.EDIT,
                notificationId = data.id
            )
        }
        dialogBinding.remove.onClick {
            viewModel.deletePriceNotification(data.id ?: 0)
            // remove and call viewmodel above after response changed
            // viewModel.getPriceNotification(
            dialog.dismiss()
        }
        dialog.show()
    }
}
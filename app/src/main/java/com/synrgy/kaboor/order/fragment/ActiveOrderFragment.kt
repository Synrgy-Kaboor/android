package com.synrgy.kaboor.order.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.common.utils.enums.OrderType
import com.synrgy.kaboor.databinding.FragmentActiveOrderBinding
import com.synrgy.kaboor.order.DetailHistoryActivity
import com.synrgy.kaboor.order.OrderViewModel
import com.synrgy.kaboor.order.adapter.OrderAdapter
import com.synrgy.kaboor.payment.PaymentMethodDetailActivity
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showEmptyState
import com.wahidabd.library.utils.extensions.showLoadingState
import com.wahidabd.library.utils.exts.observerLiveData
import org.koin.android.ext.android.inject

class ActiveOrderFragment : KaboorFragment<FragmentActiveOrderBinding>() {

    private val viewModel: OrderViewModel by inject()

    private val orderAdapter by lazy {
        OrderAdapter(requireContext(), false, ::handleOrderClick)
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean,
    ): FragmentActiveOrderBinding =
        FragmentActiveOrderBinding.inflate(layoutInflater, container, attachRoot)

    override fun initUI() {
        initActiveOrderFlight()
    }

    override fun initAction() {}

    override fun initProcess() {
        super.initProcess()
        viewModel.getActive()
    }

    override fun initObservers() {
        viewModel.order.observerLiveData(
            viewLifecycleOwner,
            onLoading = { binding.msv.showLoadingState() },
            onFailure = { _, message ->
                binding.msv.showDefaultState()
                showToast(message.toString())
            },
            onSuccess = {
                binding.msv.showDefaultState()
                if (it.isEmpty()) binding.msv.showEmptyState()
                orderAdapter.setData = it
            }
        )
    }

    // NEED ADJUST FOR NAVIGATION
    private fun handleOrderClick(data: Pair<Int, String>, type: OrderType) {
        when(type){
            OrderType.PROCESS -> PaymentMethodDetailActivity.start(requireContext(), data.first)
            OrderType.NOT_COMPLETED -> PaymentMethodDetailActivity.start(requireContext(), data.first)
            OrderType.E_TICKET -> DetailHistoryActivity.start(requireContext(), data)
            OrderType.COMPLETED -> DetailHistoryActivity.start(requireContext(), data)
        }
    }

    private fun initActiveOrderFlight() = with(binding) {
        val layoutManager =
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        rvOrder.layoutManager = layoutManager
        rvOrder.adapter = orderAdapter
    }
}
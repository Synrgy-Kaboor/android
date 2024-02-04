package com.synrgy.kaboor.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.kaboor.databinding.FragmentOrderHistoryBinding
import com.synrgy.kaboor.order.adapter.OrderAdapter
import com.synrgy.kaboor.utils.constant.ConstantDummy

class OrderHistoryFragment : KaboorFragment<FragmentOrderHistoryBinding>() {

    private val orderAdapter by lazy {
        OrderAdapter(requireContext())
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean,
    ): FragmentOrderHistoryBinding =
        FragmentOrderHistoryBinding.inflate(layoutInflater, container, attachRoot)

    override fun initUI() {
        initOrderHistoryFlight()
    }

    override fun initAction() {}

    override fun initProcess() {
        orderAdapter.setData = ConstantDummy.orderHistoryFlight()
    }

    override fun initObservers() {}

    private fun initOrderHistoryFlight() = with(binding) {
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
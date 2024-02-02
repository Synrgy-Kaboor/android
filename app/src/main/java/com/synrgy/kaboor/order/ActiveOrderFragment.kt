package com.synrgy.kaboor.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.kaboor.databinding.FragmentActiveOrderBinding
import com.synrgy.kaboor.order.adapter.OrderAdapter
import com.synrgy.kaboor.utils.constant.ConstantDummy

class ActiveOrderFragment : KaboorFragment<FragmentActiveOrderBinding>() {

    private val orderAdapter by lazy {
        OrderAdapter(requireContext())
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
        orderAdapter.setData = ConstantDummy.activeOrderFlight()
    }

    override fun initObservers() {}

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
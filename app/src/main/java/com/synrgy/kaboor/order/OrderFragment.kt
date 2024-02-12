package com.synrgy.kaboor.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.common.utils.ext.showLoginState
import com.synrgy.kaboor.authentication.login.LoginActivity
import com.synrgy.kaboor.databinding.FragmentOrderBinding
import com.synrgy.kaboor.home.SharedViewModel
import com.synrgy.kaboor.order.fragment.ActiveOrderFragment
import com.synrgy.kaboor.order.fragment.OrderHistoryFragment
import com.wahidabd.library.presentation.adapter.BaseViewPagerFragmentStateAdapter
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.fallback
import org.koin.android.ext.android.inject


class OrderFragment : KaboorFragment<FragmentOrderBinding>() {

    private val sharedViewModel: SharedViewModel by inject()

    private val viewPager by lazy {
        object : BaseViewPagerFragmentStateAdapter<BaseFragment<*>>(requireActivity()) {
            override fun createFragment(position: Int): Fragment =
                getItem(position).fallback(Fragment())
        }
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean,
    ): FragmentOrderBinding = FragmentOrderBinding.inflate(layoutInflater, container, attachRoot)

    override fun initUI() {
        setupViewPager()
    }

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

    private fun setupViewPager() = with(binding) {
        val titles = listOf("Aktif", "Riwayat")
        vpScreen.apply {
            adapter = viewPager
            isSaveEnabled = false
            isUserInputEnabled = true

            TabLayoutMediator(tabLayout, vpScreen) { tab, position ->
                tab.text = titles[position]
            }.attach()
        }

        viewPager.addAllItems(
            listOf(
                ActiveOrderFragment(),
                OrderHistoryFragment()
            )
        )
    }
}
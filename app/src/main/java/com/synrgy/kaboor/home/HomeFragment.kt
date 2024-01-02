package com.synrgy.kaboor.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.synrgy.common.navigation.ConstantMenu
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.common.utils.enums.ProductAdapterType
import com.synrgy.kaboor.databinding.FragmentHomeBinding
import com.synrgy.kaboor.home.adapter.CardProductAdapter
import com.synrgy.kaboor.home.adapter.LastSeenAdapter
import com.synrgy.kaboor.home.adapter.MenuHomeAdapter
import com.synrgy.kaboor.home.adapter.PromoAdapter
import com.synrgy.kaboor.ticket.PassengerBottomSheetFragment
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.wahidabd.library.utils.common.showToast

class HomeFragment : KaboorFragment<FragmentHomeBinding>() {

    private val menuHomeAdapter by lazy {
        MenuHomeAdapter(
            requireContext(),
            onItemClicked = { showToast(it.id.toString()) }
        )
    }

    private val promoAdapter by lazy {
        PromoAdapter(requireContext())
    }

    private val lastSeenAdapter by lazy {
        LastSeenAdapter(
            requireContext(),
            onItemClick = { showToast(it.toString()) }
        )
    }

    private val destinationAdapter by lazy {
        CardProductAdapter(
            requireContext(),
            ProductAdapterType.HOME,
            onClick = { showDialog() })
    }

    private val rentalAdapter by lazy {
        CardProductAdapter(
            requireContext(),
            ProductAdapterType.HOME,
            onClick = {}
        )
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, attachRoot)

    // TODO: For UI
    override fun initUI() {
        binding.rvDestination.adapter = destinationAdapter
        binding.rvRental.adapter = rentalAdapter

        initMenu()
        initPromo()
        initLastSeen()
    }

    // TODO: For Action (Click, Touch, etc)
    override fun initAction() {}

    // TODO: For Process (API, Call ViewModel, etc)
    // Remove dummy data if you want to use API
    override fun initProcess() {
        menuHomeAdapter.setData = ConstantMenu.homeMenus()
        promoAdapter.setData = ConstantDummy.promos()
        lastSeenAdapter.setData = ConstantDummy.lastSees()
        destinationAdapter.setData = ConstantDummy.favoriteDestinations()
        rentalAdapter.setData = ConstantDummy.rentalCars()
    }

    // TODO: For Observer (LiveData, etc)
    override fun initObservers() {}

    private fun initMenu() = with(binding) {
        val layoutManager = FlexboxLayoutManager(activity)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.CENTER
        rvMenu.layoutManager = layoutManager
        rvMenu.adapter = menuHomeAdapter
    }

    private fun initPromo() = with(binding) {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvPromo.layoutManager = layoutManager
        rvPromo.adapter = promoAdapter
    }

    private fun initLastSeen() = with(binding) {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvLastSeen.layoutManager = layoutManager
        rvLastSeen.adapter = lastSeenAdapter
    }

    // TODO: Remove this after testing
    private fun showDialog() {
        PassengerBottomSheetFragment.newInstance()
            .showBottomSheet(parentFragmentManager)
    }
}
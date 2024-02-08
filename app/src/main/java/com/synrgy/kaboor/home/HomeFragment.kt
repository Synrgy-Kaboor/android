package com.synrgy.kaboor.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.common.utils.enums.HomeMenu
import com.synrgy.common.utils.enums.ProductAdapterType
import com.synrgy.common.utils.ext.toCountDownGmt7
import com.synrgy.common.utils.ext.toEpochMillis
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.AuthViewModel
import com.synrgy.kaboor.authentication.login.LoginActivity
import com.synrgy.kaboor.databinding.FragmentHomeBinding
import com.synrgy.kaboor.home.adapter.CardProductAdapter
import com.synrgy.kaboor.home.adapter.LastSeenAdapter
import com.synrgy.kaboor.home.adapter.MenuHomeAdapter
import com.synrgy.kaboor.home.adapter.PromoAdapter
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.synrgy.kaboor.utils.navigation.NavDirection
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.observerLiveData
import org.koin.android.ext.android.inject

class HomeFragment : KaboorFragment<FragmentHomeBinding>() {

    private val viewMode: SharedViewModel by inject()

    private var loginState: Boolean = false

    private val menuHomeAdapter by lazy {
        MenuHomeAdapter(
            requireContext(),
            onItemClicked = ::handleNavigationMenu
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
            onClick = { })
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

    override fun initUI() {
        binding.rvDestination.adapter = destinationAdapter
        binding.rvRental.adapter = rentalAdapter

        initMenu()
        initPromo()
        initLastSeen()
    }

    override fun initAction() {}

    override fun initProcess() {
        viewMode.checkLogin()

        menuHomeAdapter.setData = HomeMenu.entries.toList()
        promoAdapter.setData = ConstantDummy.promos()
        lastSeenAdapter.setData = ConstantDummy.lastSees()
        destinationAdapter.setData = ConstantDummy.favoriteDestinations()
        rentalAdapter.setData = ConstantDummy.rentalCars()
    }

    override fun initObservers() {
        viewMode.login.observe(viewLifecycleOwner) { state ->
            loginState = state
        }
    }

    private fun initMenu() = with(binding) {
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

    private fun handleNavigationMenu(menu: HomeMenu) {
        if (loginState) NavDirection.navHomeDirection(menu, requireContext())
        else showAlertDialog(
            title = getString(R.string.message_login_required),
            description = getString(R.string.message_login_description),
            secondaryTextButton = getString(R.string.label_later),
            primaryTextButton = getString(R.string.label_login),
            primaryAction = { LoginActivity.start(requireContext()) }
        )
    }

}
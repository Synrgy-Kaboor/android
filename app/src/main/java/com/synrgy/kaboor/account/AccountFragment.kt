package com.synrgy.kaboor.account

import android.view.LayoutInflater
import android.view.ViewGroup
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.common.utils.ext.showLoginState
import com.synrgy.kaboor.R
import com.synrgy.kaboor.account.help.HelpCenterActivity
import com.synrgy.kaboor.authentication.AuthViewModel
import com.synrgy.kaboor.authentication.change.ChangeEmailActivity
import com.synrgy.kaboor.authentication.change.ChangePhoneNumberActivity
import com.synrgy.kaboor.authentication.login.LoginActivity
import com.synrgy.kaboor.base.SplashActivity
import com.synrgy.kaboor.databinding.FragmentAccountBinding
import com.synrgy.kaboor.home.SharedViewModel
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import org.koin.android.ext.android.inject


class AccountFragment : KaboorFragment<FragmentAccountBinding>() {

    private val sharedViewModel: SharedViewModel by inject()
    private val viewModel: AuthViewModel by inject()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean,
    ): FragmentAccountBinding =
        FragmentAccountBinding.inflate(layoutInflater, container, attachRoot)

    override fun initUI() = with(binding) {
        imgProfile.setImageResource(R.drawable.ic_launcher_foreground)
        tvUserName.text = "Andre Hudson"
    }

    override fun initAction() = with(binding) {
        profileContainer.onClick { AccountDetailActivity.start(requireContext()) }
        emailContainer.onClick { ChangeEmailActivity.start(requireContext()) }
        phoneContainer.onClick { ChangePhoneNumberActivity.start(requireContext()) }
        passportContainer.onClick { }
        customerServiceContainer.onClick { HelpCenterActivity.start(requireContext()) }
        logoutContainer.onClick { showLogoutDialog() }
    }

    override fun initProcess() {
        super.initProcess()
        sharedViewModel.checkLogin()
    }

    override fun initObservers() {
        super.initObservers()
        sharedViewModel.login.observe(viewLifecycleOwner) { state ->
            if (!state) binding.msv.showLoginState { LoginActivity.start(requireContext()) }
        }
        viewModel.logout.observerLiveData(
            viewLifecycleOwner,
            onLoading = ::showLoading,
            onFailure = { _, _ -> },
            onSuccess = {
                hideLoading()
                SplashActivity.start(requireContext())
                requireActivity().finishAffinity()
            }
        )
    }

    private fun showLogoutDialog() {
        showAlertDialog(
            title = getString(R.string.title_logout),
            description = getString(R.string.message_logout),
            primaryTextButton = getString(R.string.label_cancel),
            secondaryTextButton = getString(R.string.label_logout),
            secondaryAction = {
                viewModel.clearToken()
            }
        )
    }

}
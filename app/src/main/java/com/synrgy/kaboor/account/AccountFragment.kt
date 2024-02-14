package com.synrgy.kaboor.account

import android.view.LayoutInflater
import android.view.ViewGroup
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.common.utils.ext.showLoginState
import com.synrgy.kaboor.R
import com.synrgy.kaboor.account.help.HelpCenterActivity
import com.synrgy.kaboor.account.passport.PassportActivity
import com.synrgy.kaboor.authentication.change.ChangeEmailActivity
import com.synrgy.kaboor.authentication.change.ChangePhoneNumberActivity
import com.synrgy.kaboor.authentication.login.LoginActivity
import com.synrgy.kaboor.base.SplashActivity
import com.synrgy.kaboor.databinding.FragmentAccountBinding
import com.synrgy.kaboor.home.SharedViewModel
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import org.koin.android.ext.android.inject


class AccountFragment : KaboorFragment<FragmentAccountBinding>() {

    private val sharedViewModel: SharedViewModel by inject()
    private val viewModel: AccountViewModel by inject()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean,
    ): FragmentAccountBinding =
        FragmentAccountBinding.inflate(layoutInflater, container, attachRoot)

    override fun initUI() = with(binding) {

    }

    override fun initAction() = with(binding) {
        profileContainer.onClick { AccountDetailActivity.start(requireContext()) }
        emailContainer.onClick { ChangeEmailActivity.start(requireContext()) }
        phoneContainer.onClick { ChangePhoneNumberActivity.start(requireContext()) }
        passportContainer.onClick { PassportActivity.start(requireContext()) }
        customerServiceContainer.onClick { HelpCenterActivity.start(requireContext()) }
        logoutContainer.onClick { showLogoutDialog() }
    }

    override fun initProcess() {
        super.initProcess()
        sharedViewModel.checkLogin()
        viewModel.getUser()
        viewModel.getPercentage()
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
        viewModel.userData.observe(this) { user ->
            with(binding) {
                tvUserName.text = user.fullName
                tvEmail.text = user.email
                tvPhone.text = user.phoneNumber

                if (user.imageUrl?.isNotEmpty() == true) {
                    imgProfile.setImageUrl(requireContext(), user.imageUrl.toString())
                }

                debug { "DATA USER --> $user" }
            }
        }

        viewModel.percentage.observe(this) { percentage ->
            binding.tvProgress.text = getString(R.string.format_percentage_profile, percentage)
            binding.progressHorizontal.progress = percentage
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUser()
        viewModel.getPercentage()
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
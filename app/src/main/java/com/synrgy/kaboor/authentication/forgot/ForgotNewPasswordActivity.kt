package com.synrgy.kaboor.authentication.forgot

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.common.utils.constant.Constant
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.domain.auth.model.request.NewPasswordParam
import com.synrgy.kaboor.authentication.login.LoginActivity
import com.synrgy.kaboor.databinding.ActivityForgotNewPasswordBinding
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.customRule
import com.wahidabd.library.validation.util.notEmptyRule
import com.wahidabd.library.validation.util.regexRule
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.synrgy.common.R as commonR

class ForgotNewPasswordActivity : KaboorPassiveActivity<ActivityForgotNewPasswordBinding>() {

    private val viewModel: ForgotPasswordViewModel by viewModel()
    private var email: String? = emptyString()


    companion object {
        private const val EMAIL = "email"
        fun start(context: Context, email: String) {
            context.startActivity(
                Intent(context, ForgotNewPasswordActivity::class.java)
                    .putExtra(EMAIL, email)
            )
        }
    }

    override fun getViewBinding(): ActivityForgotNewPasswordBinding {
        return ActivityForgotNewPasswordBinding.inflate(layoutInflater)
    }

    override fun initIntent() {
        super.initIntent()
        email = intent.getStringExtra(EMAIL)
    }

    override fun initUI() {}

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        btnSave.onClick { validate() }
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.generic.observerLiveData(
            this,
            onLoading = ::showLoading,
            onFailure = {_, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = {
                hideLoading()
                LoginActivity.start(this)
                finishAffinity()
            }
        )
    }

    override fun onValidationSuccess() {
        binding.tvRule.setTextColor(getColor(commonR.color.neutral6))

        val password = binding.etPassword.getText()
        val rePassword = binding.etConfirmPassword.getText()
        val data = NewPasswordParam(email.toString(), password, rePassword)
        viewModel.newPassword(data)
    }

    override fun onValidationFailed() {
        binding.tvRule.setTextColor(getColor(commonR.color.secondaryDanger))
    }

    override fun setupValidation() = with(binding) {
        addValidation(
            Validation(
                etPassword.textInput, listOf(
                    notEmptyRule(emptyString()),
                    regexRule(emptyString(), Constant.regexAlphanumeric),
                )
            )
        )

        addValidation(
            Validation(
                etConfirmPassword.textInput, listOf(
                    notEmptyRule(emptyString()),
                    customRule(emptyString()) { etPassword.getText() == etConfirmPassword.getText() }
                )
            )
        )

    }
}
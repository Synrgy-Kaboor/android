package com.synrgy.kaboor.authentication.login

import android.content.Context
import android.content.Intent
import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.AuthViewModel
import com.synrgy.kaboor.authentication.forgot.ForgotPasswordActivity
import com.synrgy.kaboor.authentication.register.RegisterActivity
import com.synrgy.kaboor.databinding.ActivityLoginBinding
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.emailRule
import com.wahidabd.library.validation.util.notEmptyRule
import org.koin.android.ext.android.inject

class LoginActivity : KaboorPassiveActivity<ActivityLoginBinding>() {

    private val viewModel: AuthViewModel by inject()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() = with(binding) {
        btnLogin.onClick {
            validate()
        }
        tvForgotPassword.onClick { ForgotPasswordActivity.start(this@LoginActivity) }
        tvCreateAccount.onClick { RegisterActivity.start(this@LoginActivity) }
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.checkEmail.observerLiveData(
            this,
            onLoading = { showLoading() },
            onFailure = { _, message ->
                hideLoading()
                if (message?.contains(
                        getString(R.string.contains_regitered_account),
                        ignoreCase = true
                    ) == true
                ) {
                    LoginPasswordActivity.start(this@LoginActivity, binding.etEmail.getText())
                } else showErrorDialog(message.toString())
            },
            onSuccess = {
                hideLoading()
                handleLoginAccount(it)
            }
        )
    }

    private fun handleLoginAccount(response: KaboorGenericResponse) {
        if (response.code == 200) {
            showAlertDialog(
                title = getString(R.string.message_account_was_not_registered),
                description = getString(R.string.message_account_was_not_registered_description),
                primaryTextButton = getString(R.string.label_register_account),
                secondaryTextButton = getString(R.string.label_later),
                primaryAction = { LoginPasswordActivity.start(this, binding.etEmail.getText()) },
            )
        } else {
            showErrorDialog(response.message)
        }
    }

    override fun onValidationSuccess() {
        val body = EmailParam(
            binding.etEmail.getText()
        )
        viewModel.checkEmail(body)
    }

    override fun setupValidation() {
        addValidation(
            Validation(
                binding.etEmail.textInput, listOf(
                    notEmptyRule(getString(R.string.error_empty_email)),
                    emailRule(getString(R.string.error_invalid_email)),
                )
            )
        )
    }
}
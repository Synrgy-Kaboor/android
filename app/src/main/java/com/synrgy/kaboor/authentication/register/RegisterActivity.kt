package com.synrgy.kaboor.authentication.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.common.utils.ext.removeErrorTextPadding
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.AuthViewModel
import com.synrgy.kaboor.authentication.login.LoginPasswordActivity
import com.synrgy.kaboor.databinding.ActivityRegisterBinding
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.emailRule
import com.wahidabd.library.validation.util.notEmptyRule
import org.koin.android.ext.android.inject

class RegisterActivity : KaboorPassiveActivity<ActivityRegisterBinding>() {

    private val viewModel: AuthViewModel by inject()

    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityRegisterBinding =
        ActivityRegisterBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() = with(binding) {
        btnRegister.setOnClickListener { validate() }
    }

    override fun initObservers() {
        viewModel.checkEmail.observerLiveData(
            this,
            onLoading = { showLoading() },
            onFailure = { _, message ->
                hideLoading()
                handleRegisteredAccount(message)
            },
            onSuccess = {
                hideLoading()
                RegisterDetailActivity.start(this@RegisterActivity, binding.etEmail.getText())
            }
        )
    }

    private fun handleRegisteredAccount(message: String?) {
        if (message?.contains(
                getString(R.string.contains_regitered_account),
                ignoreCase = true
            ) == true
        ) {
            showAlertDialog(
                title = getString(R.string.message_account_was_registered),
                description = getString(R.string.message_account_was_registered_description),
                primaryTextButton = getString(R.string.label_login),
                secondaryTextButton = getString(R.string.label_later),
                primaryAction = { LoginPasswordActivity.start(this, binding.etEmail.getText()) },
            )
        } else {
            showErrorDialog(message.toString())
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
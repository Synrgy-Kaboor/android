package com.synrgy.kaboor.authentication.login

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.removeErrorTextPadding
import com.synrgy.common.utils.ext.setClearPaddingTextInput
import com.synrgy.domain.auth.model.request.LoginParam
import com.synrgy.kaboor.base.MainActivity
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.AuthViewModel
import com.synrgy.kaboor.databinding.ActivityLoginPasswordBinding
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.notEmptyRule
import org.koin.android.ext.android.inject

class LoginPasswordActivity : KaboorPassiveActivity<ActivityLoginPasswordBinding>() {

    private val viewModel: AuthViewModel by inject()
    private var email: String? = emptyString()

    companion object {
        private const val EXTRA_EMAIL = "extra_email"
        fun start(context: Context, email: String) {
            context.startActivity(
                Intent(context, LoginPasswordActivity::class.java)
                    .putExtra(EXTRA_EMAIL, email)
            )
        }
    }

    override fun getViewBinding(): ActivityLoginPasswordBinding =
        ActivityLoginPasswordBinding.inflate(layoutInflater)

    override fun initIntent() {
        super.initIntent()
        email = intent.getStringExtra(EXTRA_EMAIL)
    }

    override fun initUI() = with(binding) {
        etEmail.setText(email.toString())
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        btnLogin.onClick {
            validate()
        }
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.jwt.observerLiveData(
            this,
            onLoading = {
                showLoading()
            },
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = {
                hideLoading()
                viewModel.saveToken(it.jwt)
                MainActivity.start(this)
                finishAffinity()
            }
        )
    }

    override fun onValidationSuccess() = with(binding) {
        setClearPaddingTextInput(
            listOf(
                etEmail.textInput,
                etPassword.textInput,
            )
        )
        removeErrorTextPadding(
            listOf(
                etEmail.textInput,
                etPassword.textInput,
            )
        )

        val data = LoginParam(
            email = email.toString(),
            password = etPassword.getText()
        )
        viewModel.login(data)
    }

    override fun setupValidation() = with(binding) {
        addValidation(
            Validation(
                etPassword.textInput, listOf(
                    notEmptyRule(getString(R.string.error_required_password))
                )
            )
        )
    }
}
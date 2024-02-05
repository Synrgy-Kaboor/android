package com.synrgy.kaboor.authentication.login

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.removeErrorTextPadding
import com.synrgy.common.utils.ext.setClearPaddingTextInput
import com.synrgy.domain.auth.model.request.LoginParam
import com.synrgy.domain.user.model.response.User
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.AuthViewModel
import com.synrgy.kaboor.base.MainActivity
import com.synrgy.kaboor.databinding.ActivityLoginPasswordBinding
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.extensions.debug
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
            onLoading = ::showLoading,
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = {
                viewModel.saveToken(it.jwt)
                showDialog()
            }
        )

        viewModel.userData.observerLiveData(
            this,
            onLoading = ::showLoading,
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = { user ->
                hideLoading()

                debug { "LOGIN --> $user" }
                val data = User(
                    email = email.toString(),
                    fullName = user.fullName,
                    title = user.title,
                    gender = user.gender,
                    birthday = user.birthday,
                    country = user.country,
                    nation = user.nation,
                    city = user.city,
                    address = user.address,
                    phoneNumber = user.phoneNumber,
                    isWni = user.isWni,
                    verified = user.verified
                )
                debug { "LOGIN DATA --> $data" }
                viewModel.saveUserInfo(data)
                MainActivity.start(this)
                finishAffinity()
            }
        )
    }

    private fun showDialog() {
        hideLoading()
        showAlertDialog(
            isCancelable = false,
            title = getString(R.string.title_login_success),
            description = getString(R.string.message_login_success),
            primaryTextButton = getString(R.string.label_continue),
            primaryAction = {
                viewModel.getUser()
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
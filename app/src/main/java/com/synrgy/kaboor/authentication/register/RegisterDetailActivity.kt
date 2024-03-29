package com.synrgy.kaboor.authentication.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.common.utils.constant.Constant
import com.synrgy.common.utils.enums.OtpType
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.removeErrorTextPadding
import com.synrgy.common.utils.ext.setClearPaddingTextInput
import com.synrgy.domain.auth.model.request.RegisterParam
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.AuthViewModel
import com.synrgy.kaboor.authentication.otp.OtpActivity
import com.synrgy.kaboor.databinding.ActivityDetailRegisterBinding
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.notEmptyRule
import com.wahidabd.library.validation.util.regexRule
import org.koin.android.ext.android.inject

class RegisterDetailActivity : KaboorPassiveActivity<ActivityDetailRegisterBinding>() {

    private val viewModel: AuthViewModel by inject()

    private var email: String? = emptyString()

    companion object {
        private const val EXTRA_EMAIL = "extra_email"
        fun start(context: AppCompatActivity, email: String) {
            context.startActivity(
                Intent(context, RegisterDetailActivity::class.java)
                    .putExtra(EXTRA_EMAIL, email)
            )
        }
    }

    override fun getViewBinding(): ActivityDetailRegisterBinding =
        ActivityDetailRegisterBinding.inflate(layoutInflater)

    override fun initIntent() {
        super.initIntent()
        email = intent.getStringExtra(EXTRA_EMAIL)
    }

    override fun initUI() = with(binding) {
        etEmail.setText(email ?: emptyString())
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        btnCreateAccount.onClick {
            validatePassword()
            validate()
        }
    }


    override fun initObservers() {
        viewModel.generic.observerLiveData(
            this,
            onLoading = { showLoading() },
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = {
                hideLoading()
                OtpActivity.start(this@RegisterDetailActivity, OtpType.REGISTER, email.toString())
            }
        )
    }

    override fun onValidationSuccess() = with(binding) {
        setClearPaddingTextInput(
            listOf(
                etPhone.textInput,
                etEmail.textInput,
                etFullName.textInput,
                etPassword.textInput,
            )
        )
        removeErrorTextPadding(
            listOf(
                etPhone.textInput,
                etEmail.textInput,
                etFullName.textInput,
                etPassword.textInput,
            )
        )

        val data = RegisterParam(
            email = email.toString(),
            fullName = etFullName.getText(),
            password = etPassword.getText(),
            phoneNumber = etPhone.getText()
        )
        viewModel.register(data)
    }

    override fun setupValidation() = with(binding) {
        addValidation(
            Validation(
                etPhone.textInput, listOf(
                    notEmptyRule(getString(R.string.error_required_phone_number))
                )
            )
        )

        addValidation(
            Validation(
                etFullName.textInput, listOf(
                    notEmptyRule(getString(R.string.error_required_full_name))
                )
            )
        )

        addValidation(
            Validation(
                etPassword.textInput, listOf(
                    notEmptyRule(getString(R.string.error_required_password)),
                    regexRule(emptyString(), Constant.regexAlphanumeric)
                )
            )
        )
    }

    private fun validatePassword() = with(binding) {
        val password = etPassword.getText()

        if (password.length < 7) {
            tvRulePassword.setTextColor(
                ContextCompat.getColor(
                    this@RegisterDetailActivity,
                    com.wahidabd.library.R.color.colorRed
                )
            )
        }
    }
}
package com.synrgy.kaboor.authentication.forgot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.common.utils.enums.OtpType
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.otp.OtpActivity
import com.synrgy.kaboor.databinding.ActivityForgotPasswordBinding
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.emailRule
import com.wahidabd.library.validation.util.notEmptyRule
import org.koin.android.ext.android.inject

class ForgotPasswordActivity : KaboorPassiveActivity<ActivityForgotPasswordBinding>() {

    private val viewModel: ForgotPasswordViewModel by inject()

    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, ForgotPasswordActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityForgotPasswordBinding =
        ActivityForgotPasswordBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() = with(binding) {
        btnVerify.onClick { validate() }
        appbar.setOnBackClickListener { onBackPress() }
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.generic.observerLiveData(
            this,
            onLoading = { showLoading() },
            onFailure = { _, message -> showErrorDialog(message.toString()) },
            onSuccess = { navigateToOtpActivity() }
        )
    }

    private fun navigateToOtpActivity() {
        hideLoading()
        OtpActivity.start(this, OtpType.FORGOT_PASSWORD)
    }

    override fun onValidationSuccess() {
        val body = EmailParam(binding.etEmail.editText)
        viewModel.forgotPassword(body)
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
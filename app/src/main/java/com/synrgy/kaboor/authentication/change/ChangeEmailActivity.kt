package com.synrgy.kaboor.authentication.change

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.common.utils.enums.OtpType
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.otp.OtpActivity
import com.synrgy.kaboor.authentication.register.RegisterActivity
import com.synrgy.kaboor.databinding.ActivityChangeEmailBinding
import com.synrgy.kaboor.databinding.ActivityRegisterBinding
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.emailRule
import com.wahidabd.library.validation.util.notEmptyRule
import org.koin.android.ext.android.inject

class ChangeEmailActivity : KaboorPassiveActivity<ActivityChangeEmailBinding>() {

    private val viewModel: ChangeEmailViewModel by inject()
    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, ChangeEmailActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityChangeEmailBinding =
        ActivityChangeEmailBinding.inflate(layoutInflater)

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

    override fun initAction() = with(binding) {
//        btnKodeVerifikasi.onClick {
//            if (validate()){
//                ChangeEmailOTPActivity.start(this@ChangeEmailActivity)
//                appbar.setOnBackClickListener { onBackPress() }
//            }
//        }

        btnKodeVerifikasi.onClick { validate() }
        btnKodeVerifikasi.onClick { navigateToOtpActivity() }
        appbar.setOnBackClickListener { onBackPress() }
//
    }

    override fun initObservers() {
        super.initObservers()
//        viewModel.generic.observerLiveData(
//            this,
//            onLoading = { showLoading() },
//            onFailure = { _, message -> showErrorDialog(message.toString()) },
//            onSuccess = { navigateToOtpActivity() }
//        )
    }

    override fun initUI() {

    }

    override fun onValidationSuccess() {
//        val body = EmailParam(binding.etEmail.getText())
//        viewModel.changeEmail(body)
    }


    private fun navigateToOtpActivity() {
        hideLoading()
        OtpActivity.start(this, OtpType.FORGOT_PASSWORD, binding.etEmail.getText())
    }

}
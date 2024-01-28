package com.synrgy.kaboor.authentication.change

import android.content.Context
import android.content.Intent
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.common.utils.enums.OtpType
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.domain.auth.model.request.PhoneParam
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.otp.OtpActivity
import com.synrgy.kaboor.databinding.ActivityChangePhoneNumberBinding
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.notEmptyRule

class ChangePhoneNumberActivity : KaboorPassiveActivity<ActivityChangePhoneNumberBinding>() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ChangePhoneNumberActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityChangePhoneNumberBinding =
        ActivityChangePhoneNumberBinding.inflate(layoutInflater)

    override fun setupValidation() {
        addValidation(
            Validation(
                binding.etPhone.textInput, listOf(
                    notEmptyRule(getString(R.string.error_empty_phone))
                )
            )
        )
    }

    override fun initAction() = with(binding) {
        btnVerification.onClick { validate() }
        appbar.setOnBackClickListener { onBackPress() }
    }

    override fun initObservers() {
        super.initObservers()

    }

    private fun navigateToOtpActivity() {
        hideLoading()
        OtpActivity.start(this, OtpType.FORGOT_PASSWORD, binding.etPhone.getText())
    }

    override fun initUI() {

    }

    override fun onValidationSuccess() {
        val body = PhoneParam(binding.etPhone.getText())
    }


}
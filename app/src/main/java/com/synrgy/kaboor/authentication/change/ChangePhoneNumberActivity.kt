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
import org.koin.android.ext.android.inject

class ChangePhoneNumberActivity : KaboorPassiveActivity<ActivityChangePhoneNumberBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ChangePhoneNumberActivity::class.java))
        }
    }

    private val viewModel: ChangeViewModel by inject()

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
        viewModel.generic.observerLiveData(
            this,
            onLoading = { showLoading() },
            onFailure = { _, message -> showErrorDialog(message.toString()) },
            onSuccess = {
                hideLoading()
                viewModel.getUser()
            }
        )

        viewModel.userData.observe(this ){
            val user = it.copy(phoneNumber = binding.etPhone.getText())
            viewModel.saveUserInfo(user)
            OtpActivity.start(this, OtpType.CHANGE_PHONE)
        }
    }


    override fun initUI() {}

    override fun onValidationSuccess() {
        val body = PhoneParam(binding.etPhone.getText())
        viewModel.changePhone(body)
    }


}
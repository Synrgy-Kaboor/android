package com.synrgy.kaboor.authentication.change

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.register.RegisterActivity
import com.synrgy.kaboor.databinding.ActivityChangeEmailBinding
import com.synrgy.kaboor.databinding.ActivityRegisterBinding
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.emailRule
import com.wahidabd.library.validation.util.notEmptyRule

class ChangeEmailActivity : KaboorPassiveActivity<ActivityChangeEmailBinding>() {
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

    override fun initAction() {
        binding.btnKodeVerifikasi.setOnClickListener {
          if (validate()){
              moveToChangeEmailOTPActivity()
          }
        }
    }

    private fun moveToChangeEmailOTPActivity() {
        ChangeEmailOTPActivity.start(this)
    }

    override fun initUI() {

    }

    override fun onValidationSuccess() {
        moveToChangeEmailOTPActivity()
    }


}
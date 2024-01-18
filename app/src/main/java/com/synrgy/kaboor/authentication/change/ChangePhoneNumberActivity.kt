package com.synrgy.kaboor.authentication.change

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.register.RegisterActivity
import com.synrgy.kaboor.databinding.ActivityChangeEmailBinding
import com.synrgy.kaboor.databinding.ActivityChangePhoneNumberBinding
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.notEmptyRule

class ChangePhoneNumberActivity : KaboorPassiveActivity<ActivityChangePhoneNumberBinding>() {
    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, ChangePhoneNumberActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityChangePhoneNumberBinding =
        ActivityChangePhoneNumberBinding.inflate(layoutInflater)

    override fun setupValidation() {
        addValidation(
            Validation(
                binding.etPhone.textInput, listOf(
                    notEmptyRule(getString(R.string.error_empty_phone)),
                )
            )
        )
    }

    override fun initAction() {
        TODO("Not yet implemented")
    }

    override fun initUI() {

    }

    override fun onValidationSuccess() {
        TODO("Not yet implemented")
    }


}
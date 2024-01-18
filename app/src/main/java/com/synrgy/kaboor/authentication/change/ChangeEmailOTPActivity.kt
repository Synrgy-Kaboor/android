package com.synrgy.kaboor.authentication.change

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.register.RegisterActivity
import com.synrgy.kaboor.databinding.ActivityChangeEmailBinding
import com.synrgy.kaboor.databinding.ActivityOtpBinding

class ChangeEmailOTPActivity : KaboorPassiveActivity<ActivityOtpBinding>() {
    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, ChangeEmailOTPActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityOtpBinding =
        ActivityOtpBinding.inflate(layoutInflater)

    override fun setupValidation() {
        TODO("Not yet implemented")
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
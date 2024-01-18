package com.synrgy.kaboor.authentication.change

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.synrgy.common.presentation.KaboorPassiveActivity
import com.synrgy.kaboor.R
import com.synrgy.kaboor.authentication.register.RegisterActivity
import com.synrgy.kaboor.databinding.ActivityOtpBinding

class ChangePhoneOTPActivity : KaboorPassiveActivity<ActivityOtpBinding>() {
    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, ChangePhoneOTPActivity::class.java))
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

        binding.tvTitle.text = "Cek Inbox Kamu"
        binding.tvDesc.text = "Kami sudah mengirimkan kode OTP lewat Inbox kamu"

    }

    override fun onValidationSuccess() {
        TODO("Not yet implemented")
    }


}
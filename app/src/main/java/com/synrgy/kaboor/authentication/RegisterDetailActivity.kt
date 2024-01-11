package com.synrgy.kaboor.authentication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityDetailRegisterBinding
import com.synrgy.kaboor.databinding.ActivityForgotPasswordBinding

class RegisterDetailActivity : KaboorActivity<ActivityDetailRegisterBinding>() {

    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, ForgotPasswordActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityDetailRegisterBinding =
        ActivityDetailRegisterBinding.inflate(layoutInflater)

    // TODO: For handle intent (Data, etc)
    override fun initIntent() {}

    // TODO: For UI
    override fun initUI() {
        val spannableString = SpannableString("Dengan membuat akun kamu menyetujui Syarat, Ketentuan dan Kebijakan Privasi Kaboor")
        val blueSpan = ForegroundColorSpan(Color.parseColor("#3A42FF\""))
        val start = spannableString.indexOf("Syarat, Ketentuan dan Kebijakan")
        val end = start + "Syarat, Ketentuan dan Kebijakan".length
        spannableString.setSpan(blueSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.syarat.text = spannableString
        val email = intent.getStringExtra("email")
        binding.etEmail.text.append(email)
    }

    // TODO: For Action (Click, Touch, etc)
    override fun initAction() {}

    // TODO: For Process (API, Call ViewModel, etc)
    override fun initProcess() {}

    // TODO: For Observer (LiveData, etc)
    override fun initObservers() {}

}
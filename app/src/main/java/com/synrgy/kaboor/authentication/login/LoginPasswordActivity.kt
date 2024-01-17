package com.synrgy.kaboor.authentication.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.kaboor.databinding.ActivityLoginPasswordBinding
import com.wahidabd.library.utils.common.emptyString

class LoginPasswordActivity : KaboorActivity<ActivityLoginPasswordBinding>() {

    private var email: String? = emptyString()

    companion object {
        private const val EXTRA_EMAIL = "extra_email"
        fun start(context: Context, email: String) {
            context.startActivity(
                Intent(context, LoginPasswordActivity::class.java)
                    .putExtra(EXTRA_EMAIL, email)
            )
        }
    }

    override fun getViewBinding(): ActivityLoginPasswordBinding =
        ActivityLoginPasswordBinding.inflate(layoutInflater)

    override fun initIntent() {
        email = intent.getStringExtra(EXTRA_EMAIL)
    }

    override fun initUI() {}

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
    }

}
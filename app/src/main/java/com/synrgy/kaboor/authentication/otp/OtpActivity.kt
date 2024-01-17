package com.synrgy.kaboor.authentication.otp

import android.content.Context
import android.content.Intent
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.Constant
import com.synrgy.common.utils.enums.OtpType
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.setTimer
import com.synrgy.common.utils.ext.toSeconds
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityOtpBinding
import com.synrgy.kaboor.utils.navigation.NavDirection
import com.wahidabd.library.utils.exts.getCompatColor
import com.wahidabd.library.utils.exts.onClick

class OtpActivity : KaboorActivity<ActivityOtpBinding>(){

    private var otpType: OtpType? = null

    private val countDown = setTimer(
        Constant.OTP_TIMER,
        Constant.OTP_INTERVAL,
        onTick = { setSpannableCountDown(it.toSeconds()) },
        onFinish = {}
    )

    companion object {
        private const val EXTRA_DATA = "extra_data"
        fun start(context: Context, type: OtpType) {
            context.startActivity(Intent(context, OtpActivity::class.java).apply {
                putExtra(EXTRA_DATA, type)
            })
        }
    }

    override fun getViewBinding(): ActivityOtpBinding =
        ActivityOtpBinding.inflate(layoutInflater)

    override fun initIntent() {
        super.initIntent()

        otpType = intent.getSerializableExtra(EXTRA_DATA) as OtpType
    }

    override fun initUI() {
        countDown.start()
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        btnVerification.onClick { navigate() }
        otpView.setOtpCompletionListener { navigate() } // change this to viewmodel if API is ready
    }

    override fun onPause() {
        super.onPause()
        countDown.cancel()
    }

    override fun onRestart() {
        super.onRestart()
        countDown.start()
    }

    private fun navigate() {
        otpType?.let { NavDirection.navOtpDirection(it, this) }
    }

    private fun setSpannableCountDown(time: String) {
        val instructionString = getString(R.string.format_count_down_otp, time)
            .split("__", ignoreCase = true)
        binding.tvCountDown.text = (buildSpannedString {
            append(instructionString.first())
            bold {
                append(
                    instructionString[1],
                    ForegroundColorSpan(this@OtpActivity.getCompatColor(com.synrgy.common.R.color.secondary01)),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            append(instructionString[2])
        })
    }


}
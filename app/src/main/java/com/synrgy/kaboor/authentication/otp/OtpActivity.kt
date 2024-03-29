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
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.domain.auth.model.request.OtpParam
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityOtpBinding
import com.synrgy.kaboor.utils.navigation.NavDirection
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.getCompatColor
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import org.koin.android.ext.android.inject

class OtpActivity : KaboorActivity<ActivityOtpBinding>() {

    private val viewModel: OtpViewModel by inject()
    private var otpType: OtpType = OtpType.REGISTER

    private var otp: String? = emptyString()
    private var email: String? = emptyString()
    private var phone: String? = emptyString()

    private val countDown = setTimer(
        Constant.OTP_TIMER,
        Constant.TIMER_INTERVAL,
        onTick = { setSpannableCountDown(it.toSeconds()) },
        onFinish = {
            binding.btnVerification.isEnabled = false
            binding.btnSendAgain.isEnabled = true
        }
    )

    companion object {
        private const val EXTRA_DATA = "extra_data"
        private const val EXTRA_EMAIL = "email"
        private const val EXTRA_PHONE = "phone"

        fun start(
            context: Context,
            type: OtpType,
            email: String? = null,
            phone: String? = null
        ) {
            context.startActivity(Intent(context, OtpActivity::class.java).apply {
                putExtra(EXTRA_DATA, type)
                putExtra(EXTRA_EMAIL, email)
                putExtra(EXTRA_PHONE, phone)
            })
        }
    }

    override fun getViewBinding(): ActivityOtpBinding =
        ActivityOtpBinding.inflate(layoutInflater)

    override fun initIntent() {
        super.initIntent()

        otpType = intent.getSerializableExtra(EXTRA_DATA) as OtpType
        email = intent.getStringExtra(EXTRA_EMAIL)
        phone = intent.getStringExtra(EXTRA_PHONE)
    }

    override fun initUI() {
        countDown.start()
    }

    override fun initAction() = with(binding) {
        otpView.setOtpCompletionListener {
            btnVerification.isEnabled = true
            otp = it
        }
        appbar.setOnBackClickListener { onBackPress() }
        btnVerification.onClick { verifyOtp() }
        btnSendAgain.onClick { resendOtp() }
    }

    override fun initObservers() {
        viewModel.generic.observerLiveData(
            this,
            onLoading = { showLoading() },
            onFailure = { _, message -> showErrorDialog(message.toString()) },
            onSuccess = {
                hideLoading()
                viewModel.getUser()
            }
        )

        viewModel.resend.observerLiveData(
            this,
            onLoading = { showLoading() },
            onFailure = { _, message -> showErrorDialog(message.toString()) },
            onSuccess = {
                hideLoading()
                countDown.start()
                binding.btnVerification.isEnabled = true
                binding.btnSendAgain.isEnabled = false
            }
        )

        viewModel.userData.observe(this){user ->
            if (otpType == OtpType.CHANGE_PHONE) {
                val param = user.copy(phoneNumber = phone)
                viewModel.saveUserInfo(param)
            } else if (otpType == OtpType.CHANGE_EMAIL){
                val param = user.copy(email = email)
                viewModel.saveUserInfo(param)
            }

            navigate()
        }
    }

    private fun resendOtp(){
        val body = EmailParam(email.toString())
        viewModel.resendOtp(body, type = otpType)
    }

    private fun verifyOtp(){
        val body = OtpParam(email.toString(), otp.toString())
        viewModel.verifyOtp(body, otpType)
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
        otpType.let { NavDirection.navOtpDirection(it,  email, this) }
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
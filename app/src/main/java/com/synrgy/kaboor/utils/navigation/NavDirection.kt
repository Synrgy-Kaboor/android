package com.synrgy.kaboor.utils.navigation

import android.app.Activity
import android.content.Context
import com.synrgy.common.utils.enums.HomeMenu
import com.synrgy.common.utils.enums.OtpType
import com.synrgy.kaboor.authentication.forgot.ForgotNewPasswordActivity
import com.synrgy.kaboor.authentication.login.LoginPasswordActivity
import com.synrgy.kaboor.base.MainActivity
import com.synrgy.kaboor.booking.flight.FlightScheduleActivity
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 12/30/2023.
 * Github github.com/wahidabd.
 */


object NavDirection {
    fun navHomeDirection(menu: HomeMenu, context: Context) {
        when (menu) {
            HomeMenu.PLANE -> FlightScheduleActivity.start(context)
            HomeMenu.FOOD -> {}
            HomeMenu.BAGGAGE -> {}
            HomeMenu.ASSURANCE -> {}
        }
    }

    fun navOtpDirection(
        type: OtpType,
        email: String? = emptyString(),
        activity: Activity
    ) {
        when (type) {
            OtpType.FORGOT_PASSWORD -> ForgotNewPasswordActivity.start(activity, email.toString())
            OtpType.REGISTER -> {
                LoginPasswordActivity.start(activity, email.toString())
                activity.finishAffinity()
            }
            OtpType.CHANGE_EMAIL -> {
                MainActivity.start(activity)
                activity.finish()
            }
            OtpType.CHANGE_PHONE -> {
                MainActivity.start(activity)
                activity.finish()
            }
        }
    }
}
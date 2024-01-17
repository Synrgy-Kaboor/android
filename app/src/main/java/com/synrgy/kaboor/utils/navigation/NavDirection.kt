package com.synrgy.kaboor.utils.navigation

import android.content.Context
import com.synrgy.common.utils.enums.HomeMenu
import com.synrgy.common.utils.enums.OtpType
import com.synrgy.kaboor.authentication.forgot.ForgotNewPasswordActivity
import com.synrgy.kaboor.authentication.login.LoginActivity
import com.synrgy.kaboor.authentication.login.LoginPasswordActivity
import com.synrgy.kaboor.ticket.plane.FlightScheduleActivity
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
        context: Context,
        email: String? = emptyString()
    ) {
        when (type) {
            OtpType.FORGOT_PASSWORD -> ForgotNewPasswordActivity.start(context, email.toString())
            OtpType.REGISTER -> {
                LoginPasswordActivity.start(context, email.toString())
            }
        }
    }
}
package com.synrgy.kaboor.utils.navigation

import android.content.Context
import com.synrgy.common.utils.MenuHome
import com.synrgy.kaboor.ticket.plane.FlightScheduleActivity


/**
 * Created by wahid on 12/30/2023.
 * Github github.com/wahidabd.
 */


object NavHomeDirection {
    fun navDirection(data: MenuHome, context: Context) {
        when (data.id) {
            1 -> FlightScheduleActivity.start(context)
            2 -> {}
            3 -> {}
            4 -> {}
            5 -> {}
            6 -> {}
            7 -> {}
            8 -> {}
            9 -> {}
        }
    }
}
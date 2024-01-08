package com.synrgy.kaboor.utils.navigation

import android.content.Context
import com.synrgy.common.utils.enums.HomeMenu
import com.synrgy.kaboor.ticket.plane.FlightScheduleActivity
import com.synrgy.kaboor.ticket.plane.PassengerDetailActivity


/**
 * Created by wahid on 12/30/2023.
 * Github github.com/wahidabd.
 */


object NavHomeDirection {
    fun navDirection(menu: HomeMenu, context: Context) {
        when (menu) {
            HomeMenu.PLANE -> FlightScheduleActivity.start(context)
            HomeMenu.CAR -> PassengerDetailActivity.start(context) //Change this after test
            HomeMenu.BUS -> {}
            HomeMenu.TRAIN -> {}
            HomeMenu.TICKET -> {}
            HomeMenu.FOOD -> {}
            HomeMenu.AIRPORT -> {}
            HomeMenu.BAGGAGE -> {}
            HomeMenu.ASSURANCE -> {}
        }
    }
}
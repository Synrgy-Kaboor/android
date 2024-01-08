package com.synrgy.common.utils.constant

import com.synrgy.common.R
import com.synrgy.common.utils.MenuHome


/**
 * Created by wahid on 12/31/2023.
 * Github github.com/wahidabd.
 */


object ConstantMenu {
    fun homeMenus(): List<MenuHome> =
        listOf(
            MenuHome(1, R.string.label_plane, R.drawable.ic_plane),
            MenuHome(2, R.string.label_car, R.drawable.ic_car),
            MenuHome(3, R.string.label_bus, R.drawable.ic_bus),
            MenuHome(4, R.string.label_train, R.drawable.ic_train),
            MenuHome(5, R.string.label_ticket, R.drawable.ic_ticket),
            MenuHome(6, R.string.label_food_resevation, R.drawable.ic_food),
            MenuHome(7, R.string.label_airport_express, R.drawable.ic_airport),
            MenuHome(8, R.string.label_add_baggage, R.drawable.ic_baggage),
            MenuHome(9, R.string.label_assurance, R.drawable.ic_assurance),
        )
}
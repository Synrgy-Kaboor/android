package com.synrgy.common.utils.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.synrgy.common.R


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


enum class HomeMenu(@StringRes val label: Int, @DrawableRes val icon: Int) {
    PLANE(R.string.label_plane, R.drawable.ic_plane),
    CAR(R.string.label_car, R.drawable.ic_car),
    BUS(R.string.label_bus, R.drawable.ic_bus),
    TRAIN(R.string.label_train, R.drawable.ic_train),
    TICKET(R.string.label_ticket, R.drawable.ic_ticket),
    FOOD(R.string.label_food_resevation, R.drawable.ic_food),
    AIRPORT(R.string.label_airport_express, R.drawable.ic_airport),
    BAGGAGE(R.string.label_add_baggage, R.drawable.ic_baggage),
    ASSURANCE(R.string.label_assurance, R.drawable.ic_assurance),
}
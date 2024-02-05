package com.synrgy.common.utils.enums

import androidx.annotation.StringRes
import com.synrgy.common.R


/**
 * Created by wahid on 1/26/2024.
 * Github github.com/wahidabd.
 */


enum class DetailPassengerType(@StringRes val label: Int){
    BOOKER(R.string.label_booker_detail),
    PASSENGER(R.string.label_passenger_detail),
    PASSENGER_BOOKER(R.string.label_passenger_detail)
}
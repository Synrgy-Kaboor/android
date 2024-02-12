package com.synrgy.common.model

import androidx.annotation.ArrayRes


/**
 * Created by wahid on 2/10/2024.
 * Github github.com/wahidabd.
 */


data class BankData(
    val methodName: String,
    val code: String,
    val imageUrl: String,
    @ArrayRes val atm: Int,
    @ArrayRes val internet: Int,
    @ArrayRes val mobile: Int
)
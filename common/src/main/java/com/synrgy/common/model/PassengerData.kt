package com.synrgy.common.model


/**
 * Created by wahid on 1/5/2024.
 * Github github.com/wahidabd.
 */


data class PassengerData(
    var mature: Int? = 1,
    var kid: Int? = 0,
    var baby: Int? = 0,
){
    fun count(): Int {
        return mature?.plus(kid ?: 0)?.plus(baby ?: 0) ?: 0
    }
}
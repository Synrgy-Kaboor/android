package com.synrgy.data.flight.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wahidabd.library.base.Model


/**
 * Created by wahid on 2/3/2024.
 * Github github.com/wahidabd.
 */


@Entity(tableName = "flight")
data class AirportEntity(
    @PrimaryKey
    val id: Int,
    val code: String,
    val name: String,
    val timezone: Int
)
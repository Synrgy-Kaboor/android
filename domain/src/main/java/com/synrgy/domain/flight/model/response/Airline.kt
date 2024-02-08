package com.synrgy.domain.flight.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by wahid on 2/4/2024.
 * Github github.com/wahidabd.
 */


@Parcelize
data class Airline(
    val id: Int,
    val name: String,
    val imageUrl: String
): Parcelable

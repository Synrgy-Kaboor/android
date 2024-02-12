package com.synrgy.domain.flight.model.response

import android.os.Parcelable
import com.wahidabd.library.utils.common.emptyString
import kotlinx.parcelize.Parcelize


/**
 * Created by wahid on 2/4/2024.
 * Github github.com/wahidabd.
 */


@Parcelize
data class Plane(
    val id: Int? = 0,
    val name: String? = emptyString(),
    val code: String? = emptyString(),
    val airline: Airline?
): Parcelable

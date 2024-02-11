package com.synrgy.common.utils.enums

import androidx.annotation.StringRes
import com.synrgy.common.R


/**
 * Created by wahid on 1/23/2024.
 * Github github.com/wahidabd.
 */


enum class ClipboardType(@StringRes val message: Int) {
    ACCOUNT_NUMBER(R.string.copy_account_number),
    TOTAL_PAYMENT(R.string.copy_total_payment),
    BOOKING_CODE(R.string.copy_booking_code),
}
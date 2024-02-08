package com.synrgy.domain.promo.mapper

import com.synrgy.data.promo.model.response.VoucherResponse
import com.synrgy.domain.promo.model.response.Voucher


/**
 * Created by wahid on 2/6/2024.
 * Github github.com/wahidabd.
 */


fun VoucherResponse.toDomain(): Voucher {
    return Voucher(
        id = id,
        code = code,
        title = title,
        description = description,
        eligiblePaymentMethods = eligiblePaymentMethods,
        maximumDiscount = maximumDiscount,
        expiredTime = expiredTime
    )
}

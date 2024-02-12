package com.synrgy.domain.promo

import com.synrgy.domain.promo.model.response.Voucher
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/22/2024.
 * Github github.com/wahidabd.
 */


interface PromoUseCase {
    suspend fun getVoucher(): Flow<Resource<List<Voucher>>>
}
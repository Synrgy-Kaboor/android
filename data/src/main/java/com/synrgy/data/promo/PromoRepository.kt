package com.synrgy.data.promo

import com.synrgy.common.data.ListWrapper
import com.synrgy.data.promo.model.response.VoucherResponse
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 2/6/2024.
 * Github github.com/wahidabd.
 */


interface PromoRepository {
    suspend fun getVoucher(): Flow<Resource<ListWrapper<VoucherResponse>>>
}
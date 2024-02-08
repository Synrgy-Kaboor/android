package com.synrgy.data.promo

import com.synrgy.common.data.ListWrapper
import com.synrgy.common.utils.ext.flowDispatcherIO
import com.synrgy.data.promo.model.response.VoucherResponse
import com.synrgy.data.promo.remote.PromoService
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by wahid on 2/6/2024.
 * Github github.com/wahidabd.
 */


class PromoDataStore(
    private val api: PromoService,
    private val error: ErrorParser
) : PromoRepository {

    override suspend fun getVoucher(): Flow<Resource<ListWrapper<VoucherResponse>>> = flow {
        enqueue(
            error::convertGenericError,
            api::geVoucher,
            onEmit = { data -> emit(data) }
        )
    }.flowDispatcherIO()

}
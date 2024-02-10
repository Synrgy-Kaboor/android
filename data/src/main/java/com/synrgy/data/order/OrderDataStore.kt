package com.synrgy.data.order

import com.synrgy.common.data.ListWrapper
import com.synrgy.common.utils.ext.flowDispatcherIO
import com.synrgy.data.order.model.response.FlightOrderResponse
import com.synrgy.data.order.model.response.OrderResponse
import com.synrgy.data.order.remote.OrderService
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


class OrderDataStore(
    private val api: OrderService,
    private val error: ErrorParser
) : OrderRepository {

    override suspend fun getActive(): Flow<Resource<ListWrapper<OrderResponse>>> = flow {
        enqueue(
            error::convertGenericError,
            api::getActive,
            onEmit = { data -> emit(data) }
        )
    }.flowDispatcherIO()

    override suspend fun getFinished(): Flow<Resource<ListWrapper<OrderResponse>>> = flow {
        enqueue(
            error::convertGenericError,
            api::getFinished,
            onEmit = { data -> emit(data) }
        )
    }.flowDispatcherIO()

}
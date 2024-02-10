package com.synrgy.data.order

import com.synrgy.common.data.ListWrapper
import com.synrgy.data.order.model.response.OrderResponse
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


interface OrderRepository {

    suspend fun getActive(): Flow<Resource<ListWrapper<OrderResponse>>>
    suspend fun getFinished(): Flow<Resource<ListWrapper<OrderResponse>>>

}
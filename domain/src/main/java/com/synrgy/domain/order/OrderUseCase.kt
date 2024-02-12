package com.synrgy.domain.order

import com.synrgy.domain.order.model.response.Order
import com.synrgy.domain.order.model.response.TicketDetail
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


interface OrderUseCase {
    suspend fun getActive(): Flow<Resource<List<Order>>>
    suspend fun getFinished(): Flow<Resource<List<Order>>>
    suspend fun getOutbound(id: Int): Flow<Resource<TicketDetail>>
    suspend fun getReturn(id: Int): Flow<Resource<TicketDetail>>
}
package com.synrgy.domain.order

import com.synrgy.common.data.ListWrapper
import com.synrgy.common.data.ResponseWrapper
import com.synrgy.data.order.OrderRepository
import com.synrgy.data.order.model.response.OrderResponse
import com.synrgy.data.order.model.response.TicketDetailResponse
import com.synrgy.domain.order.mapper.toDomain
import com.synrgy.domain.order.model.response.Order
import com.synrgy.domain.order.model.response.TicketDetail
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


class OrderInteractor(private val repository: OrderRepository) : OrderUseCase {

    override suspend fun getActive(): Flow<Resource<List<Order>>> {
        return object : InternetBoundResource<List<Order>, ListWrapper<OrderResponse>>() {
            override suspend fun createCall(): Flow<Resource<ListWrapper<OrderResponse>>> {
                return repository.getActive()
            }

            override suspend fun saveCallRequest(data: ListWrapper<OrderResponse>): List<Order> {
                return data.data.map { it.toDomain() }
            }

        }.asFlow()
    }

    override suspend fun getFinished(): Flow<Resource<List<Order>>> {
        return object : InternetBoundResource<List<Order>, ListWrapper<OrderResponse>>() {
            override suspend fun createCall(): Flow<Resource<ListWrapper<OrderResponse>>> {
                return repository.getFinished()
            }

            override suspend fun saveCallRequest(data: ListWrapper<OrderResponse>): List<Order> {
                return data.data.map { it.toDomain() }
            }

        }.asFlow()
    }

    override suspend fun getOutbound(id: Int): Flow<Resource<TicketDetail>> {
        return object : InternetBoundResource<TicketDetail, ResponseWrapper<TicketDetailResponse>>(){
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<TicketDetailResponse>>> {
                return repository.getOutbound(id)
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<TicketDetailResponse>): TicketDetail {
                return data.data.toDomain()
            }
        }.asFlow()
    }

    override suspend fun getReturn(id: Int): Flow<Resource<TicketDetail>> {
        return object : InternetBoundResource<TicketDetail, ResponseWrapper<TicketDetailResponse>>(){
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<TicketDetailResponse>>> {
                return repository.getReturn(id)
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<TicketDetailResponse>): TicketDetail {
                return data.data.toDomain()
            }
        }.asFlow()
    }

    override suspend fun downloadOutboundTicket(id: Int): Flow<Resource<ResponseBody>> {
        return repository.downloadOutboundTicket(id)
    }

    override suspend fun downloadReturnTicket(id: Int): Flow<Resource<ResponseBody>> {
        return repository.downloadReturnTicket(id)
    }
}
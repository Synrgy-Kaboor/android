package com.synrgy.domain.order

import com.synrgy.common.data.ListWrapper
import com.synrgy.data.order.OrderRepository
import com.synrgy.data.order.model.response.OrderResponse
import com.synrgy.domain.order.mapper.toDomain
import com.synrgy.domain.order.model.response.Order
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow


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
}
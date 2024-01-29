package com.synrgy.domain.notification

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.data.notification.NotificationRepository
import com.synrgy.data.notification.model.response.PriceNotificationResponse
import com.synrgy.domain.notification.mapper.toDomain
import com.synrgy.domain.notification.mapper.toRequest
import com.synrgy.domain.notification.model.request.PriceNotificationParam
import com.synrgy.domain.notification.model.response.PriceNotification
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


class NotificationInteractor(
    private val repository: NotificationRepository
): NotificationUseCase {

    override suspend fun getPriceNotification(): Flow<Resource<PriceNotification>> {
        return object : InternetBoundResource<PriceNotification, ResponseWrapper<PriceNotificationResponse>>(){
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<PriceNotificationResponse>>> {
                return repository.getPriceNotification()
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<PriceNotificationResponse>): PriceNotification {
                return data.data.toDomain()
            }

        }.asFlow()
    }

    override suspend fun createPriceNotification(
        body: PriceNotificationParam
    ): Flow<Resource<KaboorGenericResponse>> {
        return repository.createPriceNotification(body.toRequest())
    }

    override suspend fun updatePriceNotification(
        id: Int,
        body: PriceNotificationParam
    ): Flow<Resource<KaboorGenericResponse>> {
        return repository.updatePriceNotification(id, body.toRequest())
    }

    override suspend fun deletePriceNotification(
        id: Int
    ): Flow<Resource<KaboorGenericResponse>> {
        return repository.deletePriceNotification(id)
    }
}
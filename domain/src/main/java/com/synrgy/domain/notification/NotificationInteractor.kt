package com.synrgy.domain.notification

import com.synrgy.common.data.ListWrapper
import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.data.notification.NotificationRepository
import com.synrgy.data.notification.model.response.NotificationResponse
import com.synrgy.data.notification.model.response.PriceNotificationResponse
import com.synrgy.domain.notification.mapper.toDomain
import com.synrgy.domain.notification.mapper.toRequest
import com.synrgy.domain.notification.model.request.PriceNotificationParam
import com.synrgy.domain.notification.model.response.Notification
import com.synrgy.domain.notification.model.response.PriceNotification
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


class NotificationInteractor(
    private val repository: NotificationRepository,
) : NotificationUseCase {

    override suspend fun getPriceNotification(): Flow<Resource<List<PriceNotification>>> {
        return object :
            InternetBoundResource<List<PriceNotification>, ListWrapper<PriceNotificationResponse>>() {
            override suspend fun createCall(): Flow<Resource<ListWrapper<PriceNotificationResponse>>> {
                return repository.getPriceNotification()
            }

            override suspend fun saveCallRequest(data: ListWrapper<PriceNotificationResponse>): List<PriceNotification> {
                return data.data.map { it.toDomain() }
            }

        }.asFlow()
    }

    override suspend fun createPriceNotification(
        body: PriceNotificationParam,
    ): Flow<Resource<KaboorResponse>> {
        return repository.createPriceNotification(body.toRequest())
    }

    override suspend fun updatePriceNotification(
        id: Int,
        body: PriceNotificationParam,
    ): Flow<Resource<KaboorResponse>> {
        return repository.updatePriceNotification(id, body.toRequest())
    }

    override suspend fun deletePriceNotification(
        id: Int,
    ): Flow<Resource<KaboorResponse>> {
        return repository.deletePriceNotification(id)
    }

    override suspend fun getNotification(): Flow<Resource<List<Notification>>> {
        return object :
            InternetBoundResource<List<Notification>, ResponseWrapper<NotificationResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<NotificationResponse>>> {
                return repository.getNotification()
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<NotificationResponse>): List<Notification> {
                return data.data.notification.map {
                    it.toDomain()
                }
            }
        }.asFlow()
    }

    override suspend fun markNotification(id: Int): Flow<Resource<KaboorResponse>> {
        TODO("Not yet implemented")
    }
}
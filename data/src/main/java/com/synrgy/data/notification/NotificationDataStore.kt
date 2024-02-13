package com.synrgy.data.notification

import com.synrgy.common.data.ListWrapper
import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.common.utils.ext.flowDispatcherIO
import com.synrgy.data.notification.model.request.PriceNotificationRequest
import com.synrgy.data.notification.model.response.NotificationResponse
import com.synrgy.data.notification.model.response.PriceNotificationResponse
import com.synrgy.data.notification.remote.NotificationService
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


class NotificationDataStore(
    private val api: NotificationService,
    private val error: ErrorParser
) : NotificationRepository {

    override suspend fun getPriceNotification(): Flow<Resource<ListWrapper<PriceNotificationResponse>>> =
        flow {
            enqueue(
                error::convertGenericError,
                api::getPriceNotification,
                onEmit = { data -> emit(data) }
            )
        }.flowDispatcherIO()

    override suspend fun createPriceNotification(
        body: PriceNotificationRequest
    ): Flow<Resource<KaboorResponse>> = flow {
        enqueue(
            body,
            error::convertGenericError,
            api::createPriceNotification,
            onEmit = { data -> emit(data) }
        )
    }

    override suspend fun updatePriceNotification(
        id: Int,
        body: PriceNotificationRequest
    ): Flow<Resource<KaboorResponse>> = flow {
        enqueue(
            id,
            body,
            error::convertGenericError,
            api::updatePriceNotification,
            onEmit = { data -> emit(data) }
        )
    }

    override suspend fun deletePriceNotification(
        id: Int
    ): Flow<Resource<KaboorResponse>> = flow {
        enqueue(
            id,
            error::convertGenericError,
            api::deletePriceNotification,
            onEmit = { data -> emit(data) }
        )
    }

    override suspend fun getNotification(): Flow<Resource<ResponseWrapper<NotificationResponse>>> =
        flow {
            enqueue(
                error::convertGenericError,
                api::getNotification,
                onEmit = { data -> emit(data) }
            )
        }.flowDispatcherIO()

    override suspend fun markNotification(id: Int): Flow<Resource<KaboorResponse>> =
        flow {
            enqueue(
                id,
                error::convertGenericError,
                api::markNotification,
                onEmit = { data -> emit(data) }
            )
        }.flowDispatcherIO()
}
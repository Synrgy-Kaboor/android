package com.synrgy.data.notification

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.data.notification.model.request.PriceNotificationRequest
import com.synrgy.data.notification.model.response.PriceNotificationResponse
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


interface NotificationRepository {

    suspend fun getPriceNotification(): Flow<Resource<ResponseWrapper<PriceNotificationResponse>>>

    suspend fun createPriceNotification(
        body: PriceNotificationRequest
    ): Flow<Resource<KaboorResponse>>

    suspend fun updatePriceNotification(
        id: Int,
        body: PriceNotificationRequest
    ): Flow<Resource<KaboorResponse>>

    suspend fun deletePriceNotification(
        id: Int
    ): Flow<Resource<KaboorResponse>>
}
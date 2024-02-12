package com.synrgy.domain.notification


import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.domain.notification.model.request.PriceNotificationParam
import com.synrgy.domain.notification.model.response.PriceNotification
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


interface NotificationUseCase {
    suspend fun getPriceNotification(): Flow<Resource<PriceNotification>>

    suspend fun createPriceNotification(
        body: PriceNotificationParam
    ): Flow<Resource<KaboorResponse>>

    suspend fun updatePriceNotification(
        id: Int,
        body: PriceNotificationParam
    ): Flow<Resource<KaboorResponse>>

    suspend fun deletePriceNotification(
        id: Int
    ): Flow<Resource<KaboorResponse>>
}
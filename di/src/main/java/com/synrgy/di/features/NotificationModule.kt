package com.synrgy.di.features

import com.synrgy.data.notification.NotificationDataStore
import com.synrgy.data.notification.NotificationRepository
import com.synrgy.data.notification.remote.NotificationService
import com.synrgy.domain.notification.NotificationInteractor
import com.synrgy.domain.notification.NotificationUseCase
import org.koin.dsl.module
import retrofit2.Retrofit


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


val notificationModule = module {
    single<NotificationRepository> { NotificationDataStore(get(), get()) }
    single<NotificationUseCase> { NotificationInteractor(get()) }
}
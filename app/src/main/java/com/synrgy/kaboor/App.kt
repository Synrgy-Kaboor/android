package com.synrgy.kaboor

import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.synrgy.common.utils.FirebaseCrashlyticsTree
import com.synrgy.di.appModule
import com.synrgy.di.features.authModule
import com.synrgy.di.features.bookingModule
import com.synrgy.di.features.flightModule
import com.synrgy.di.features.notificationModule
import com.synrgy.di.features.promoModule
import com.synrgy.di.features.userModule
import com.synrgy.di.retrofitModule
import com.synrgy.kaboor.utils.di.viewModelModule
import com.wahidabd.library.presentation.BaseApplication
import org.koin.core.module.Module
import timber.log.Timber


/**
 * Created by wahid on 12/27/2023.
 * Github github.com/wahidabd.
 */


class App : BaseApplication() {
    override fun getDefineModule(): List<Module> =
        listOf(
            appModule,
            retrofitModule,
            authModule,
            userModule,
            bookingModule,
            notificationModule,
            flightModule,
            promoModule,
            viewModelModule, // make sure this module is still at the bottom of the features module
        )

    override fun initApp() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val tree = if (BuildConfig.DEBUG) Timber.DebugTree()
        else FirebaseCrashlyticsTree(FirebaseCrashlytics.getInstance())

        Timber.plant(tree)
    }
}
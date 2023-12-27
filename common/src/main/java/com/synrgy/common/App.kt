package com.synrgy.common

import androidx.appcompat.app.AppCompatDelegate
import com.wahidabd.library.presentation.BaseApplication
import org.koin.core.module.Module
import timber.log.Timber


/**
 * Created by wahid on 12/27/2023.
 * Github github.com/wahidabd.
 */


class App : BaseApplication() {
    override fun getDefineModule(): List<Module> =
        listOf()

    override fun initApp() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Timber.plant(Timber.DebugTree())
    }
}
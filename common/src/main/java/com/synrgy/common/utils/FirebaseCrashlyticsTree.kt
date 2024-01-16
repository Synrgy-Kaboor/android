package com.synrgy.common.utils

import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber


/**
 * Created by wahid on 1/16/2024.
 * Github github.com/wahidabd.
 */

class FirebaseCrashlyticsTree (
    private val crashlytics: FirebaseCrashlytics
): Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        crashlytics.log(message)
    }

}
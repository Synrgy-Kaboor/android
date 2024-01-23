package com.synrgy.common.utils.ext

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.presentation.KaboorFragment


/**
 * Created by wahid on 1/17/2024.
 * Github github.com/wahidabd.
 */


fun AppCompatActivity.requestMultiplePermission(
    permissions: Array<String>,
    requestCode: Int,
    doIfGranted: (() -> Unit)? = null,
) {
    val deniedPermissions = mutableListOf<String>()
    permissions.forEach { permission ->
        if (!isGranted(permission)) {
            deniedPermissions.add(permission)
        }
    }
    if (deniedPermissions.isNotEmpty()) {
        ActivityCompat.requestPermissions(this, deniedPermissions.toTypedArray(), requestCode)
    } else {
        doIfGranted?.invoke()
    }
}

fun AppCompatActivity.isGranted(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}
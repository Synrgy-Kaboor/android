package com.synrgy.common.utils.ext

import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


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

object PermissionExt {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    val imagePermissionsAndroid13 = arrayOf(
        android.Manifest.permission.READ_MEDIA_IMAGES
    )

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    val imagePermissionsAndroid14 = arrayOf(
        android.Manifest.permission.READ_MEDIA_IMAGES,
        android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
    )

    val imagePermissionAndroid12L = arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    val notificationPermission13 = arrayOf(
        android.Manifest.permission.POST_NOTIFICATIONS
    )

    val takeCapturePermission12L = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    val takeCapturePermission14 = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_MEDIA_IMAGES,
        android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
    )

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    val takeCapturePermission13 = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.READ_MEDIA_IMAGES
    )

    const val NOTIFICATION_REQUEST_CODE = 112233
    const val IMAGE_REQUEST_CODE = 112234
    const val CAPTURE_REQUEST_CODE = 112235
}
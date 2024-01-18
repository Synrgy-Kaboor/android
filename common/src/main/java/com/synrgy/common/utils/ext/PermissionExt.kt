package com.synrgy.common.utils.ext

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.synrgy.common.presentation.KaboorActivity


/**
 * Created by wahid on 1/17/2024.
 * Github github.com/wahidabd.
 */

fun KaboorActivity<*>.requestMultiplePermission(
    permissions: Array<String>,
    onAllowed: () -> Unit,
    onNeedPermissionRationale: () -> Unit,
    onDenied: () -> Unit
){
    launchPermission(permissions, onAllowed, onNeedPermissionRationale, onDenied)
}

fun AppCompatActivity.initPermissionLauncher(
    onAllowed: (() -> Unit)?,
    onNeedPermissionRationale: (() -> Unit)?,
    onDenied: (() -> Unit)?
): ActivityResultLauncher<Array<String>> {
    return registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionResult ->
        val allPermissionGranted = permissionResult.all { it.value }
        val shouldRational = permissionResult.map {
            shouldShowRequestPermissionRationale(it.key)
        }.all { it }

        when {
            allPermissionGranted -> {
                onAllowed?.invoke()
            }

            shouldRational -> {
                onNeedPermissionRationale?.invoke()
            }

            else -> {
                onDenied?.invoke()
            }
        }
    }
}
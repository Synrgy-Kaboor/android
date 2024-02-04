package com.synrgy.common.utils.ext

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


/**
 * Created by wahid on 1/28/2024.
 * Github github.com/wahidabd.
 */

private fun Context.networkStateFlow() = callbackFlow {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            trySend(true)
        }

        override fun onLost(network: Network) {
            trySend(false)
        }
    }

    connectivityManager.registerDefaultNetworkCallback(networkCallback)
    awaitClose {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}

fun AppCompatActivity.observeNetwork(state: (Boolean) -> Unit) {
    lifecycleScope.launch {
        networkStateFlow().collect{isConnected ->
            state.invoke(isConnected)
        }
    }
}

fun<T> Flow<T>.flowDispatcherIO() = this.flowOn(Dispatchers.IO)
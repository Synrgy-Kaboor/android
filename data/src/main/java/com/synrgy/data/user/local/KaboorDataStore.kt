package com.synrgy.data.user.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.synrgy.common.utils.constant.ConstDataStore
import com.synrgy.data.user.model.request.UserRequest
import com.synrgy.data.user.model.response.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


class KaboorDataStore(context: Context) {

    companion object {
        private val TOKEN = stringPreferencesKey(ConstDataStore.PREF_TOKEN)
        private val FULL_NAME = stringPreferencesKey(ConstDataStore.PREF_FULL_NAME)
        private val EMAIL = stringPreferencesKey(ConstDataStore.PREF_EMAIL)
        private val PHONE = stringPreferencesKey(ConstDataStore.PREF_PHONE)

        @Volatile
        private var INSTANCE: KaboorDataStore? = null

        fun getInstance(context: Context): KaboorDataStore {
            return INSTANCE ?: synchronized(this) {
                val instance = KaboorDataStore(context)
                INSTANCE = instance
                instance
            }
        }
    }

    private val Context.dataStore by preferencesDataStore(ConstDataStore.KABOOR_DATA_STORE)
    private val dataStore = context.dataStore

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    fun getToken(): String? = runBlocking {
        dataStore.data.map { preferences ->
            preferences[TOKEN]
        }.first()
    }

    suspend fun setUser(data: UserRequest) {
        dataStore.edit { preferences ->
            preferences[FULL_NAME] = data.fullName
            preferences[EMAIL] = data.email
            preferences[PHONE] = data.phoneNumber
        }
    }

    fun getUser(): Flow<UserResponse> {
        return dataStore.data.map { preferences ->
            UserResponse(
                preferences[FULL_NAME].orEmpty(),
                preferences[EMAIL].orEmpty(),
                preferences[PHONE].orEmpty()
            )
        }
    }
}
package com.synrgy.data.db

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.synrgy.common.utils.constant.ConstDataStore
import com.synrgy.data.user.model.request.UserRequest
import com.synrgy.data.user.model.response.UserDataResponse
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
        private val LOGIN = booleanPreferencesKey(ConstDataStore.PREF_LOGIN)
        private val FULL_NAME = stringPreferencesKey(ConstDataStore.PREF_FULL_NAME)
        private val EMAIL = stringPreferencesKey(ConstDataStore.PREF_EMAIL)
        private val PHONE = stringPreferencesKey(ConstDataStore.PREF_PHONE)
        private val TITLE = stringPreferencesKey(ConstDataStore.PREF_title)
        private val BIRTHDAY = stringPreferencesKey(ConstDataStore.PREF_BIRTHDAY)
        private val NATION = stringPreferencesKey(ConstDataStore.PREF_NATION)
        private val CITY = stringPreferencesKey(ConstDataStore.PREF_CITY)
        private val ADDRESS = stringPreferencesKey(ConstDataStore.PREF_ADDRESS)
        private val GENDER = stringPreferencesKey(ConstDataStore.PREF_GENDER)
        private val WNI = stringPreferencesKey(ConstDataStore.PREF_WNI)

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

    suspend fun getLogin(isLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[LOGIN] = isLogin
        }
    }

    fun getLogin(): Flow<Boolean>{
        return dataStore.data.map { preferences ->
            preferences[LOGIN] ?: false
        }
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    suspend fun clearToken(){
        dataStore.edit { preferences ->
            preferences.remove(TOKEN)
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

    fun getUser(): Flow<UserDataResponse> {
        return dataStore.data.map { preferences ->
            UserDataResponse(
                preferences[FULL_NAME].orEmpty(),
                preferences[EMAIL].orEmpty(),
                preferences[PHONE].orEmpty()
            )
        }
    }
}
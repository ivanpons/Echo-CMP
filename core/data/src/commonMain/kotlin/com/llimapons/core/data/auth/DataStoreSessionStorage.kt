package com.llimapons.core.data.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.llimapons.core.data.dto.AuthInfoSerializable
import com.llimapons.core.data.mappers.toDomain
import com.llimapons.core.data.mappers.toSerializable
import com.llimapons.core.domain.auth.AuthInfo
import com.llimapons.core.domain.auth.SessionStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class DataStoreSessionStorage(
    private val dataStore: DataStore<Preferences>
): SessionStorage {

    private val authInfoKey = stringPreferencesKey("KEY_AUTH_INFO")
    private val json = Json {
        ignoreUnknownKeys = true
    }

    override fun observeAuthInfo(): Flow<AuthInfo?> {
        return dataStore.data.map { preferences ->
            val serializable = preferences[authInfoKey]
            serializable?.let{
                json.decodeFromString<AuthInfoSerializable>(serializable).toDomain()
            }
        }
    }

    override suspend fun set(info: AuthInfo?) {
        if (info == null){
            dataStore.edit {
                it.remove(authInfoKey)
            }
            return
        }
        val serializable = json.encodeToString(info.toSerializable())
        dataStore.edit { prefs ->
            prefs[authInfoKey] = serializable
        }
    }
}
package com.example.proxima.data.repository

import android.content.Context
import android.provider.Settings.Global.putString
import android.view.contentcapture.ContentCaptureContext
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val PREFERANCE_NAME="preferences"
class DataStoreRepository(context: Context){

    // At the top level of your kotlin file:
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore( PREFERANCE_NAME )

    val LOGIN_COUNTER = intPreferencesKey("login")

    fun ReadfromDataStore(context: Context) {

        val exampleCounterFlow: Flow<Int> = context.dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[LOGIN_COUNTER] ?: 0
            }
    }

    suspend fun writeToDataStore(context: Context) {
        context.dataStore.edit { settings ->
            val currentCounterValue = settings[LOGIN_COUNTER] ?: 0
            settings[LOGIN_COUNTER] = currentCounterValue + 1
        }
    }
}
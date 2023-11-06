package com.example.learndrawing.classes

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreDataPhotos(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("storeDataPhotos")
        val PHOTOS_PERSPECTIVE = stringPreferencesKey("store_data_photos_perspective")
        val PHOTOS_LIGHT_SHADOW = stringPreferencesKey("store_data_photos_light")
        val PHOTOS_LINES = stringPreferencesKey("store_data_photos_lines")
        val PHOTOS_VOLUME = stringPreferencesKey("store_data_photos_volume")
    }

    private val gson: Gson = Gson()

    suspend fun saveDataPerspective(list: MutableList<Uri>) {
        context.dataStore.edit { preferences ->
            val jsonString = gson.toJson(list)
            Log.d("PERSPSAVE", jsonString)
            preferences[PHOTOS_PERSPECTIVE] = jsonString
        }
    }
    suspend fun saveDataLightShadow(list: MutableList<Uri>) {
        context.dataStore.edit { preferences ->
            val jsonString = gson.toJson(list)
            preferences[PHOTOS_LIGHT_SHADOW] = jsonString
        }
    }
    suspend fun saveDataLines(list: MutableList<Uri>) {
        context.dataStore.edit { preferences ->
            val jsonString = gson.toJson(list)
            preferences[PHOTOS_LINES] = jsonString
        }
    }
    suspend fun saveDataVolume(list: MutableList<Uri>) {
        context.dataStore.edit { preferences ->
            val jsonString = gson.toJson(list)
            preferences[PHOTOS_VOLUME] = jsonString
        }
    }

    suspend fun deleteData(){
        context.dataStore.edit {
            it.clear()
        }
    }

    val getDataPerspective: Flow<MutableList<Uri>> = context.dataStore.data
        .map { preferences ->
            val jsonString = preferences[PHOTOS_PERSPECTIVE] ?: ""
            Log.d("PERSPGET", jsonString)
            gson.fromJson(jsonString, Array<Uri>::class.java).toMutableList()
        }
    val getDataLightShadow: Flow<MutableList<Uri>> = context.dataStore.data
        .map { preferences ->
            val jsonString = preferences[PHOTOS_LIGHT_SHADOW] ?: ""
            gson.fromJson(jsonString, Array<Uri>::class.java).toMutableList()
        }
    val getDataLines: Flow<MutableList<Uri>> = context.dataStore.data
        .map { preferences ->
            val jsonString = preferences[PHOTOS_LINES] ?: ""
            gson.fromJson(jsonString, Array<Uri>::class.java).toMutableList()
        }
    val getDataVolume: Flow<MutableList<Uri>> = context.dataStore.data
        .map { preferences ->
            val jsonString = preferences[PHOTOS_VOLUME] ?: ""
            gson.fromJson(jsonString, Array<Uri>::class.java).toMutableList()
        }

    fun isKeyStored(key: Preferences.Key<String>): Flow<Boolean> =
        context.dataStore.data.map {
                preference -> preference.contains(key)
        }
}
package com.example.learndrawing.classes_light

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreDataLight(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("storeDataLight")
        val OBJECT = stringPreferencesKey("store_data_light")
    }

    private val gson: Gson = Gson()

    suspend fun saveData(list: MutableList<Boolean>) {
        context.dataStore.edit { preferences ->
            val jsonString = gson.toJson(list)
            preferences[OBJECT] = jsonString
        }
    }

    suspend fun deleteData(){
        context.dataStore.edit {
            it.clear()
        }
    }

    val getData: Flow<MutableList<Boolean>> = context.dataStore.data
        .map { preferences ->
            val jsonString = preferences[OBJECT] ?: ""
            gson.fromJson(jsonString, Array<Boolean>::class.java).toMutableList()
        }

    fun isKeyStored(key: Preferences.Key<String>): Flow<Boolean> =
        context.dataStore.data.map {
                preference -> preference.contains(key)
        }
}
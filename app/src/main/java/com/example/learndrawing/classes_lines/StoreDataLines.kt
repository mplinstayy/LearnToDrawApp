package com.example.learndrawing.classes_lines

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreDataLines (private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("storeDataLines")
        val OBJECT = stringPreferencesKey("store_data_lines")
    }

    private val gson: Gson = Gson()

    suspend fun saveData(list: MutableList<Boolean>) {
        context.dataStore.edit { preferences ->
            val jsonString = gson.toJson(list)
            //Log.d("DATASTORE", jsonString)
            preferences[OBJECT] = jsonString
        }
    }

    suspend fun deteleData(){
        context.dataStore.edit {
            it.clear()
        }
    }

    val getData: Flow<MutableList<Boolean>> = context.dataStore.data
        .map { preferences ->
            val jsonString = preferences[OBJECT] ?: ""
            //Log.d("DATASTOREGET", jsonString)
            gson.fromJson(jsonString, Array<Boolean>::class.java).toMutableList()
        }

    fun isKeyStored(key: Preferences.Key<String>): Flow<Boolean> =
        context.dataStore.data.map {
                preference -> preference.contains(key)
        }
}
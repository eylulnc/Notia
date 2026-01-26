package com.github.eylulnc.notia.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.github.eylulnc.notia.data.storage.GoalStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AndroidGoalStorage(
    private val context: Context
) : GoalStorage {

    private val KEY = stringPreferencesKey("goal")

    override fun observe(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[KEY]
        }
    }

    override suspend fun save(text: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY] = text
        }
    }

    override suspend fun delete() {
        context.dataStore.edit { prefs ->
            prefs.remove(KEY)
        }
    }
}

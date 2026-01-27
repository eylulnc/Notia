package com.github.eylulnc.notia.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.github.eylulnc.notia.data.storage.FocusStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

class AndroidFocusStorage(
    private val context: Context
) : FocusStorage {

    override suspend fun save(date: LocalDate, text: String) {
        context.dataStore.edit { prefs ->
            prefs[stringPreferencesKey(date.toString())] = text
        }
    }

    override suspend fun delete(date: LocalDate) {
        context.dataStore.edit { prefs ->
            prefs.remove(stringPreferencesKey(date.toString()))
        }
    }

    override fun observeAll(): Flow<Map<LocalDate, String>> {
        return context.dataStore.data.map { prefs ->
            prefs.asMap()
                .mapNotNull { (key, value) ->
                    val date = runCatching { LocalDate.parse(key.name) }.getOrNull()
                    val text = value as? String
                    if (date != null && text != null) {
                        date to text
                    } else null
                }
                .toMap()
        }
    }

    override suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}

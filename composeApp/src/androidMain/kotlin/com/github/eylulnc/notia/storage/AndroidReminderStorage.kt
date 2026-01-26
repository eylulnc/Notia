package com.github.eylulnc.notia.storage

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.github.eylulnc.notia.data.storage.ReminderStorage
import com.github.eylulnc.notia.domain.model.ReminderSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalTime

private val ENABLED = booleanPreferencesKey("reminder_enabled")
private val TIME = stringPreferencesKey("reminder_time")

class AndroidReminderStorage(
    private val context: Context
) : ReminderStorage {

    override fun observe(): Flow<ReminderSettings> {
        return context.dataStore.data.map { prefs ->
            val enabled = prefs[ENABLED] ?: false
            val time = prefs[TIME]
                ?.let { LocalTime.parse(it) }
                ?: LocalTime(9, 0)

            ReminderSettings(
                isEnabled = enabled,
                time = time
            )
        }
    }

    override suspend fun enable(time: LocalTime) {
        context.dataStore.edit { prefs ->
            prefs[ENABLED] = true
            prefs[TIME] = time.toString()
        }
    }

    override suspend fun disable() {
        context.dataStore.edit { prefs ->
            prefs[ENABLED] = false
        }
    }
}

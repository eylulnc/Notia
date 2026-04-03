package com.github.eylulnc.notia.feature.settings.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.github.eylulnc.notia.domain.model.ReminderSettings
import com.github.eylulnc.notia.storage.dataStore
import com.github.eylulnc.notia.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalTime

private val THEME_MODE = stringPreferencesKey("theme_mode")
private val ONBOARDING_SEEN = booleanPreferencesKey("onboarding_seen")
private val REMINDER_ENABLED = booleanPreferencesKey("reminder_enabled")
private val REMINDER_TIME = stringPreferencesKey("reminder_time")

interface SettingsRepository {
    val themeMode: Flow<ThemeMode>
    suspend fun setThemeMode(mode: ThemeMode)
    val hasSeenOnboarding: Flow<Boolean>
    suspend fun setOnboardingSeen()
    val reminderSettings: Flow<ReminderSettings>
    suspend fun setReminderEnabled(enabled: Boolean)
    suspend fun setReminderTime(time: LocalTime)
}

class SettingsRepositoryImpl(
    private val context: Context
) : SettingsRepository {
    override val themeMode: Flow<ThemeMode> =
        context.dataStore.data.map { prefs ->
            prefs[THEME_MODE]
                ?.let { ThemeMode.valueOf(it) }
                ?: ThemeMode.SYSTEM
        }

    override suspend fun setThemeMode(mode: ThemeMode) {
        context.dataStore.edit {
            it[THEME_MODE] = mode.name
        }
    }

    override val hasSeenOnboarding: Flow<Boolean> =
        context.dataStore.data.map { prefs ->
            prefs[ONBOARDING_SEEN] ?: false
        }

    override suspend fun setOnboardingSeen() {
        context.dataStore.edit { it[ONBOARDING_SEEN] = true }
    }

    override val reminderSettings: Flow<ReminderSettings> =
        context.dataStore.data.map { prefs ->
            ReminderSettings(
                isEnabled = prefs[REMINDER_ENABLED] ?: false,
                time = prefs[REMINDER_TIME]?.let { LocalTime.parse(it) } ?: LocalTime(8, 30)
            )
        }

    override suspend fun setReminderEnabled(enabled: Boolean) {
        context.dataStore.edit { it[REMINDER_ENABLED] = enabled }
    }

    override suspend fun setReminderTime(time: LocalTime) {
        context.dataStore.edit { it[REMINDER_TIME] = time.toString() }
    }
}
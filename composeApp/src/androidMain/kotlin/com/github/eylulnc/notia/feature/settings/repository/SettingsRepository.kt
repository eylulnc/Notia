package com.github.eylulnc.notia.feature.settings.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.github.eylulnc.notia.storage.dataStore
import com.github.eylulnc.notia.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val THEME_MODE = stringPreferencesKey("theme_mode")

class SettingsRepository(
    private val context: Context
) {
    val themeMode: Flow<ThemeMode> =
        context.dataStore.data.map { prefs ->
            prefs[THEME_MODE]
                ?.let { ThemeMode.valueOf(it) }
                ?: ThemeMode.SYSTEM
        }

    suspend fun setThemeMode(mode: ThemeMode) {
        context.dataStore.edit {
            it[THEME_MODE] = mode.name
        }
    }
}
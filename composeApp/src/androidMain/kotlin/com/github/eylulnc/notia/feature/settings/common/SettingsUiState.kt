package com.github.eylulnc.notia.feature.settings.common

import com.github.eylulnc.notia.ui.theme.ThemeMode
import kotlinx.datetime.LocalTime

data class SettingsUiState(
    val themeMode: ThemeMode,
    val appVersion: String,
    val isResetDialogVisible: Boolean = false,
    val isReminderEnabled: Boolean = false,
    val reminderTime: LocalTime = LocalTime(8, 30)
)
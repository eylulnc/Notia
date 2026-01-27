package com.github.eylulnc.notia.feature.settings.common

import com.github.eylulnc.notia.ui.theme.ThemeMode

data class SettingsUiState(
    val themeMode: ThemeMode,
    val appVersion: String,
    val isResetDialogVisible: Boolean = false
)
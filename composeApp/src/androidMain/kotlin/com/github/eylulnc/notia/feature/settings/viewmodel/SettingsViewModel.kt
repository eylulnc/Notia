package com.github.eylulnc.notia.feature.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.eylulnc.notia.domain.repository.FocusRepository
import com.github.eylulnc.notia.feature.settings.common.SettingsUiState
import com.github.eylulnc.notia.feature.settings.repository.SettingsRepository
import com.github.eylulnc.notia.notifications.ReminderManager
import com.github.eylulnc.notia.ui.theme.ThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
    private val focusRepository: FocusRepository,
    private val reminderManager: ReminderManager,
    private val appVersion: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SettingsUiState(
            themeMode = ThemeMode.SYSTEM,
            appVersion = appVersion
        )
    )
    val uiState: StateFlow<SettingsUiState> = _uiState

    init {
        viewModelScope.launch {
            settingsRepository.themeMode.collect { mode ->
                _uiState.update {
                    it.copy(themeMode = mode)
                }
            }
        }

        viewModelScope.launch {
            settingsRepository.reminderSettings.collect { settings ->
                _uiState.update {
                    it.copy(
                        isReminderEnabled = settings.isEnabled,
                        reminderTime = settings.time
                    )
                }
            }
        }
    }

    fun onThemeSelected(mode: ThemeMode) {
        viewModelScope.launch {
            settingsRepository.setThemeMode(mode)
        }
    }

    fun onReminderEnabledChanged(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setReminderEnabled(enabled)
            if (enabled) {
                reminderManager.scheduleReminder(_uiState.value.reminderTime)
            } else {
                reminderManager.cancelReminder()
            }
        }
    }

    fun onReminderTimeChanged(time: LocalTime) {
        viewModelScope.launch {
            settingsRepository.setReminderTime(time)
            if (_uiState.value.isReminderEnabled) {
                reminderManager.scheduleReminder(time)
            }
        }
    }

    fun requestReset() {
        _uiState.update { it.copy(isResetDialogVisible = true) }
    }

    fun cancelReset() {
        _uiState.update { it.copy(isResetDialogVisible = false) }
    }

    fun confirmReset() {
        viewModelScope.launch {
            focusRepository.clearAll()
            _uiState.update { it.copy(isResetDialogVisible = false) }
        }
    }
}
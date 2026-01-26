package com.github.eylulnc.notia.ui.today

data class TodayUiState(
    val dateLabel: String,
    val focusText: String?,
    val currentStreak: Int,
    val isEditing: Boolean = false,
    val isLoading: Boolean = false
)
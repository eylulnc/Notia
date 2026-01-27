package com.github.eylulnc.notia.feature.history.ui

data class HistoryMonthGroup(
    val monthLabel: String, // "October 2023"
    val items: List<HistoryItem>
)

data class HistoryUiState(
    val months: List<HistoryMonthGroup>,
    val currentStreak: Int,
    val longestStreak: Int,
    val isLoading: Boolean = false
) {
    val isEmpty: Boolean get() = months.isEmpty()

    companion object {
        fun loading() = HistoryUiState(
            months = emptyList(),
            currentStreak = 0,
            longestStreak = 0,
            isLoading = true
        )
    }
}

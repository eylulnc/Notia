package com.github.eylulnc.notia.feature.history.ui

data class HistoryUiState(
    val items: List<HistoryItem>
) {
    val isEmpty: Boolean
        get() = items.isEmpty()
}

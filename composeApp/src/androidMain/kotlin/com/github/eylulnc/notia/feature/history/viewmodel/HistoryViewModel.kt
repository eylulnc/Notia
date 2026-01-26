package com.github.eylulnc.notia.feature.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.eylulnc.notia.domain.repository.FocusRepository
import com.github.eylulnc.notia.feature.history.ui.HistoryItem
import com.github.eylulnc.notia.feature.history.ui.HistoryUiState
import com.github.eylulnc.notia.util.HistoryDateFormatter
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(
    private val focusRepository: FocusRepository,
    private val dateFormatter: HistoryDateFormatter
) : ViewModel() {

    val uiState = focusRepository
        .getHistory()
        .map { focuses ->
            HistoryUiState(
                items = focuses.map { focus ->
                    HistoryItem(
                        dateLabel = dateFormatter.format(focus.date),
                        focusText = focus.text
                    )
                }
            )
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            HistoryUiState(items = emptyList())
        )
}

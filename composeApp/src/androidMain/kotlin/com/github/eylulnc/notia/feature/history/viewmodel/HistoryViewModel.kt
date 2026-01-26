package com.github.eylulnc.notia.feature.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.eylulnc.notia.domain.repository.FocusRepository
import com.github.eylulnc.notia.feature.history.ui.HistoryItem
import com.github.eylulnc.notia.feature.history.ui.HistoryMonthGroup
import com.github.eylulnc.notia.feature.history.ui.HistoryUiState
import com.github.eylulnc.notia.util.HistoryDateFormatter
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(
    private val focusRepository: FocusRepository,
    private val dateFormatter: HistoryDateFormatter
) : ViewModel() {

    val uiState = combine(
        focusRepository.getHistory(),
        focusRepository.getCurrentStreak(),
        focusRepository.getLongestStreak()
    ) { history, current, longest ->

        val grouped = history
            .groupBy { it.date.year to it.date.month }
            .toSortedMap(compareByDescending { it.first * 12 + it.second.ordinal })
            .map { (yearMonth, items) ->
                HistoryMonthGroup(
                    monthLabel = dateFormatter.format(
                        year = yearMonth.first,
                        month = yearMonth.second
                    ),
                    items = items.map {
                        HistoryItem(
                            date = it.date,
                            dateLabel = dateFormatter.format(it.date),
                            focusText = it.text
                        )
                    }
                )
            }

        HistoryUiState(
            months = grouped,
            currentStreak = current,
            longestStreak = longest
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        HistoryUiState.empty()
    )

}

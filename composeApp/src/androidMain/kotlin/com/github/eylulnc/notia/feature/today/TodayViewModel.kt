package com.github.eylulnc.notia.feature.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.eylulnc.notia.domain.repository.FocusRepository
import com.github.eylulnc.notia.util.DateProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodayViewModel(
    private val focusRepository: FocusRepository,
    private val dateProvider: DateProvider
) : ViewModel() {

    private val isEditing = MutableStateFlow(false)

    val uiState = combine(
        focusRepository.getTodayFocus(),
        focusRepository.getCurrentStreak(),
        isEditing
    ) { focus, streak, editing ->
        val today = dateProvider.today()

        TodayUiState(
            dateLabel = today.dayOfWeek.name,
            focusText = focus?.text,
            currentStreak = streak,
            isEditing = editing,
            isLoading = false
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        TodayUiState(
            dateLabel = "",
            focusText = null,
            currentStreak = 0,
            isLoading = true
        )
    )

    fun startEditing() {
        isEditing.value = true
    }

    fun cancelEditing() {
        isEditing.value = false
    }

    fun saveFocus(text: String) {
        viewModelScope.launch {
            focusRepository.setTodayFocus(text)
            isEditing.value = false
        }
    }

    fun clearFocus() {
        viewModelScope.launch {
            focusRepository.deleteTodayFocus()
        }
    }
}

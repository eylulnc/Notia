package com.github.eylulnc.notia.ui.today

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun TodayScreen(
    modifier: Modifier = Modifier,
    viewModel: TodayViewModel
) {
    val state by viewModel.uiState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {

        when {
            state.isEditing -> {
                TodayEditScreen(
                    initialText = state.focusText,
                    onCancel = viewModel::cancelEditing,
                    onSave = viewModel::saveFocus
                )
            }

            state.focusText == null -> {
                TodayEmptyState(
                    onSetFocus = viewModel::startEditing
                )
            }

            else -> {
                TodayFilledState(
                    focusText = state.focusText!!,
                    streak = state.currentStreak,
                    onEdit = viewModel::startEditing,
                    onClear = viewModel::clearFocus
                )
            }
        }
    }
}

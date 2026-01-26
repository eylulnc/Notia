package com.github.eylulnc.notia.ui.today

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.github.eylulnc.notia.ui.theme.BackgroundLight

@Composable
fun TodayScreen(
    modifier: Modifier = Modifier,
    viewModel: TodayViewModel
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            if (state.isEditing) {
                TodayEditTopBar(onCancel = viewModel::cancelEditing)
            } else {
                TodayTopBar(state.currentStreak)
            }
        },
        containerColor = BackgroundLight
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                state.isEditing -> {
                    TodayEditScreen(
                        initialText = state.focusText,
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
}

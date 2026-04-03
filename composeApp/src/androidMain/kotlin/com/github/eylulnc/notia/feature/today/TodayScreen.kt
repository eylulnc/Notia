package com.github.eylulnc.notia.feature.today

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.NotiaTheme
import com.github.eylulnc.notia.ui.theme.ThemeMode
import org.koin.androidx.compose.koinViewModel

@Composable
fun TodayScreen(
    modifier: Modifier = Modifier,
    viewModel: TodayViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var showInfoDialog by remember { mutableStateOf(false) }

    if (showInfoDialog) {
        AlertDialog(
            onDismissRequest = { showInfoDialog = false },
            title = {
                Text(
                    text = stringResource(R.string.info_sheet_title),
                    fontSize = FontSizes.subTitle,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.info_sheet_body),
                    fontSize = FontSizes.body,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            confirmButton = {
                TextButton(onClick = { showInfoDialog = false }) {
                    Text("OK")
                }
            },
            containerColor = MaterialTheme.colorScheme.background
        )
    }

    TodayScreenContent(
        state = state,
        modifier = modifier,
        onInfoClick = { showInfoDialog = true },
        cancelEditing = viewModel::cancelEditing,
        saveFocus = viewModel::saveFocus,
        startEditing = viewModel::startEditing,
        clearFocus = viewModel::clearFocus
    )
}


@Composable
internal fun TodayScreenContent(
    state: TodayUiState,
    modifier: Modifier = Modifier,
    onInfoClick: () -> Unit = {},
    cancelEditing: () -> Unit = {},
    saveFocus: (text: String) -> Unit,
    startEditing: () -> Unit,
    clearFocus: () -> Unit,
) {

    Scaffold(
        topBar = {
            if (state.isLoading) {
                // Empty TopBar or subtle loading state
            } else if (state.isEditing) {
                TodayEditTopBar(onCancel = cancelEditing)
            } else {
                TodayTopBar(currentStreak = state.currentStreak, onInfoClick = onInfoClick)
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                state.isEditing -> {
                    TodayEditScreen(
                        initialText = state.focusText,
                        onSave = saveFocus
                    )
                }

                state.focusText == null -> {
                    TodayEmptyState(
                        onSetFocus = startEditing
                    )
                }

                else -> {
                    TodayFilledState(
                        focusText = state.focusText,
                        onEdit = startEditing,
                        onClear = clearFocus
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Today – Edit",
    showBackground = true,
    backgroundColor = 0xFFFDFCF8
)
@Composable
fun TodayScreenEditPreview() {
    NotiaTheme(ThemeMode.LIGHT) {
        TodayScreenContent(
            state = TodayUiState(
                focusText = "Hello",
                isEditing = true,
                isLoading = false,
                currentStreak = 1,
                dateLabel = "28 May"
            ),
            modifier = Modifier,
            cancelEditing = {},
            saveFocus = {},
            startEditing = {},
            clearFocus = {}
        )

    }
}



@Preview(
    name = "Today – Empty",
    showBackground = true,
    backgroundColor = 0xFFFDFCF8
)
@Composable
fun TodayScreenEmptyPreview() {
    NotiaTheme(ThemeMode.LIGHT) {
        TodayScreenContent(
            state = TodayUiState(
                focusText = null,
                isEditing = false,
                isLoading = false,
                currentStreak = 1,
                dateLabel = ""
            ),
            modifier = Modifier,
            cancelEditing = {},
            saveFocus = {},
            startEditing = {},
            clearFocus = {}
        )

    }
}

@Preview(
    name = "Today – Filled",
    showBackground = true,
    backgroundColor = 0xFFFDFCF8
)
@Composable
fun TodayScreenFilledPreview() {
    NotiaTheme(ThemeMode.LIGHT) {
        TodayScreenContent(
            state = TodayUiState(
                focusText = "Hello",
                isEditing = false,
                isLoading = false,
                currentStreak = 1,
                dateLabel = ""
            ),
            modifier = Modifier,
            cancelEditing = {},
            saveFocus = {},
            startEditing = {},
            clearFocus = {}
        )

    }
}


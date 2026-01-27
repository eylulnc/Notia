package com.github.eylulnc.notia.feature.history.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.feature.history.viewmodel.HistoryViewModel
import com.github.eylulnc.notia.ui.common.NotiaTopBar
import com.github.eylulnc.notia.ui.theme.BackgroundLight
import com.github.eylulnc.notia.ui.theme.Primary
import kotlinx.datetime.LocalDate
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    HistoryScreenContent(state = state, modifier = modifier)
}


@Composable
internal fun HistoryScreenContent(
    state: HistoryUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            NotiaTopBar(
                title = stringResource(R.string.history_title)
            )
        }
    ) { padding ->
        Box(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Primary
                )
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    HistoryStreakSummary(
                        current = state.currentStreak,
                        longest = state.longestStreak
                    )

                    when {
                        state.isEmpty -> HistoryEmptyState()
                        else -> HistoryList(state.months)
                    }
                }
            }
        }
    }
}

@Preview(
    name = "History – Loading",
    showBackground = true,
    backgroundColor = 0xFFFDFCF8
)
@Composable
fun HistoryScreenLoadingPreview() {
    HistoryScreenContent(
        state = HistoryUiState.loading()
    )
}

@Preview(
    name = "History – Long List",
    showBackground = true,
    backgroundColor = 0xFFFDFCF8,
    device = "spec:width=411dp,height=891dp"
)
@Composable
fun HistoryScreenLongListPreview() {
    HistoryScreenContent(
        state = HistoryUiState(
            currentStreak = 6,
            longestStreak = 14,
            months = listOf(
                HistoryMonthGroup(
                    monthLabel = "January 2026",
                    items = List(10) { index ->
                        HistoryItem(
                            date = LocalDate(2026, 1, 27 - index),
                            dateLabel = "Mon, Jan ${27 - index}",
                            focusText = "Focus entry number ${index + 1}"
                        )
                    }
                ),
                HistoryMonthGroup(
                    monthLabel = "December 2025",
                    items = List(12) { index ->
                        HistoryItem(
                            date = LocalDate(2025, 12, 31 - index),
                            dateLabel = "Tue, Dec ${31 - index}",
                            focusText = "Daily intention ${index + 1}"
                        )
                    }
                ),
                HistoryMonthGroup(
                    monthLabel = "November 2025",
                    items = List(8) { index ->
                        HistoryItem(
                            date = LocalDate(2025, 11, 30 - index),
                            dateLabel = "Wed, Nov ${30 - index}",
                            focusText = "Another calm focus ${index + 1}"
                        )
                    }
                )
            ),
            isLoading = false
        )
    )
}

@Preview(
    name = "History – Empty",
    showBackground = true,
    backgroundColor = 0xFFFDFCF8
)
@Composable
fun HistoryScreenEmptyPreview() {
    HistoryScreenContent(
        state = HistoryUiState(
            currentStreak = 0,
            longestStreak = 0,
            months = emptyList(),
            isLoading = false
        )
    )
}

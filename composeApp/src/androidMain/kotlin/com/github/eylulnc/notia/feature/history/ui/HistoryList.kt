package com.github.eylulnc.notia.feature.history.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.eylulnc.notia.ui.theme.Spacing

@Composable
fun HistoryList(
    months: List<HistoryMonthGroup>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = Spacing.l,
            end = Spacing.l,
            bottom = Spacing.xxl
        ),
        verticalArrangement = Arrangement.spacedBy(Spacing.xl)
    ) {
        months.forEach { month ->
            item {
                HistoryMonthHeader(month.monthLabel)
            }
            item {
                HistoryMonthGroupCard(month.items)
            }
        }
    }
}

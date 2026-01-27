package com.github.eylulnc.notia.feature.history.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.eylulnc.notia.ui.theme.Cream
import com.github.eylulnc.notia.ui.theme.Spacing

@Composable
fun HistoryMonthGroupCard(
    items: List<HistoryItem>
) {
    Column(
        modifier = Modifier
            .background(
                color = Cream,
                shape = RoundedCornerShape(Spacing.l)
            )
            .padding(vertical = Spacing.s)
    ) {
        items.forEachIndexed { index, item ->
            HistoryRow(item)

            if (index < items.lastIndex) {
                Spacer(Modifier.height(Spacing.s))
            }
        }
    }
}


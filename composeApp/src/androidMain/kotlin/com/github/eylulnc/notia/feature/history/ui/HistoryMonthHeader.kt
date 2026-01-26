package com.github.eylulnc.notia.feature.history.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.eylulnc.notia.ui.theme.CharcoalSoft
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.Spacing

@Composable
fun HistoryMonthHeader(
    label: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Spacing.l, bottom = Spacing.s),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label.uppercase(),
            fontSize = FontSizes.caption,
            color = CharcoalSoft
        )
        Spacer(Modifier.width(Spacing.m))
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = CharcoalSoft.copy(alpha = 0.2f)
        )
    }
}

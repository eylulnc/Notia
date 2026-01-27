package com.github.eylulnc.notia.feature.history.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.NotiaTheme
import com.github.eylulnc.notia.ui.theme.Spacing
import com.github.eylulnc.notia.ui.theme.ThemeMode

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
            fontWeight = FontWeight.Normal,
            letterSpacing = Spacing.letterWide,
            fontSize = FontSizes.label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.width(Spacing.m))
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
        )
    }
}

@Preview
@Composable
fun HistoryMonthHeaderPreview() {
    NotiaTheme(themeMode = ThemeMode.LIGHT) {
        HistoryMonthHeader("May 2023")
    }
}
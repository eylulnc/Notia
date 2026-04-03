package com.github.eylulnc.notia.feature.history.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.Spacing


@Composable
fun HistoryRow(
    item: HistoryItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Spacing.l,
                vertical = Spacing.s
            ),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.width(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.dateLabel.substringBefore(","),
                fontSize = FontSizes.label,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = item.dateLabel.substringAfterLast(" "),
                fontSize = FontSizes.label,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(Modifier.width(Spacing.l))

        Text(
            text = item.focusText,
            fontSize = FontSizes.label,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

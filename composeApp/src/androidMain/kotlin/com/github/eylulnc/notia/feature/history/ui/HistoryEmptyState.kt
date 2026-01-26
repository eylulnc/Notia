package com.github.eylulnc.notia.feature.history.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.theme.CharcoalSoft
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.Spacing

@Composable
fun HistoryEmptyState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.l),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.history_empty),
            fontSize = FontSizes.body,
            color = CharcoalSoft,
            textAlign = TextAlign.Center
        )
    }
}

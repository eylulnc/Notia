package com.github.eylulnc.notia.feature.history.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.theme.Charcoal
import com.github.eylulnc.notia.ui.theme.CharcoalSoft
import com.github.eylulnc.notia.ui.theme.Cream
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.Spacing

@Composable
fun HistoryStreakSummary(
    current: Int,
    longest: Int
) {
    Row(
        modifier = Modifier
            .padding(horizontal = Spacing.l, vertical = Spacing.m)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.l)
    ) {
        StreakStat(
            label = stringResource(R.string.history_current_streak),
            value = current
        )
        StreakStat(
            label = stringResource(R.string.history_longest_streak),
            value = longest
        )
    }
}

@Composable
private fun StreakStat(
    label: String,
    value: Int
) {
    Column(
        modifier = Modifier
            .background(
                color = Cream,
                shape = RoundedCornerShape(Spacing.l)
            )
            .padding(Spacing.l)
    ) {
        Text(
            text = label.uppercase(),
            fontSize = FontSizes.caption,
            color = CharcoalSoft
        )
        Spacer(Modifier.height(Spacing.s))
        Text(
            text = value.toString(),
            fontSize = FontSizes.subTitle,
            fontWeight = FontWeight.Bold,
            color = Charcoal
        )
    }
}


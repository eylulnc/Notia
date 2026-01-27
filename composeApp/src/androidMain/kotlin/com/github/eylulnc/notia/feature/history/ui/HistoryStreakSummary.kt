package com.github.eylulnc.notia.feature.history.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.NotiaTheme
import com.github.eylulnc.notia.ui.theme.Spacing
import com.github.eylulnc.notia.ui.theme.ThemeMode

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
            modifier = Modifier.weight(1f),
            label = stringResource(R.string.history_current_streak),
            value = current
        )

        StreakStat(
            modifier = Modifier.weight(1f),
            label = stringResource(R.string.history_longest_streak),
            value = longest
        )
    }
}


@Composable
private fun StreakStat(
    modifier: Modifier = Modifier,
    label: String,
    value: Int
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f),
                shape = RoundedCornerShape(Spacing.l)
            )
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(Spacing.l))
            .padding(Spacing.l),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label.uppercase(),
            fontSize = FontSizes.caption,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            letterSpacing = Spacing.letterWide,
        )

        Spacer(Modifier.height(Spacing.s))

        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontSize = FontSizes.subTitle,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    append(value.toString())
                }

                append(" ")

                withStyle(
                    SpanStyle(
                        fontSize = FontSizes.caption,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    append(stringResource(R.string.days))
                }
            }
        )

    }
}

@Preview
@Composable
fun HistoryStreakPreview(){
    NotiaTheme(themeMode = ThemeMode.LIGHT) {
        HistoryStreakSummary(current = 10, longest = 15)
    }
}

package com.github.eylulnc.notia.feature.today

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.common.NotiaTextButton
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.FontSizes.LINE_HEIGHT_MULTIPLIER
import com.github.eylulnc.notia.ui.theme.NotiaTheme
import com.github.eylulnc.notia.ui.theme.Spacing
import com.github.eylulnc.notia.ui.theme.ThemeMode


@Composable
fun TodayFilledState(
    focusText: String,
    onEdit: () -> Unit,
    onClear: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = Spacing.l)
            .padding(top = Spacing.l)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = focusText,
            fontSize = FontSizes.midTitle,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            lineHeight = FontSizes.midTitle * LINE_HEIGHT_MULTIPLIER
        )

        Spacer(Modifier.height(Spacing.xl))

        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.l),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NotiaTextButton(onClick = onEdit) {
                Icon(Icons.Outlined.Edit, contentDescription = null)
                Spacer(Modifier.width(Spacing.xs))
                Text(stringResource(R.string.edit))
            }

            NotiaTextButton(onClick = onClear) {
                Icon(Icons.Outlined.Close, contentDescription = null)
                Spacer(Modifier.width(Spacing.xs))
                Text(stringResource(R.string.clear))
            }
        }

    }
}

@Preview
@Composable
fun TodayFilledStatePreview() {
    NotiaTheme(themeMode = ThemeMode.LIGHT) {
        TodayFilledState("Hello asdfsdf sddsafafds dsfadfssd sdaadsdsf sdfadsf", onEdit = {}, onClear = {})
    }
}

package com.github.eylulnc.notia.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.theme.CharcoalSoft
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.Spacing


@Composable
fun AppInfoSection(
    appVersion: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.xl),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = FontSizes.caption,
            letterSpacing = Spacing.letterWide,
            color = CharcoalSoft
        )

        Spacer(Modifier.height(Spacing.xs))

        Text(
            text = stringResource(
                R.string.settings_version,
                appVersion
            ),
            fontSize = FontSizes.caption,
            color = CharcoalSoft.copy(alpha = 0.7f)
        )
    }
}

@Preview
@Composable
fun AppInfoPreview() {
    AppInfoSection("1.1.4")
}
package com.github.eylulnc.notia.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.NotiaTheme
import com.github.eylulnc.notia.ui.theme.Spacing
import com.github.eylulnc.notia.ui.theme.ThemeMode


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
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(Spacing.xs))

        Text(
            text = stringResource(
                R.string.settings_version,
                appVersion
            ),
            fontSize = FontSizes.caption,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
        )

        Spacer(Modifier.height(Spacing.xs))

        Text(
            text = stringResource(R.string.settings_developer),
            fontSize = FontSizes.caption,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
        )
    }
}

@Preview
@Composable
fun AppInfoPreview() {
    NotiaTheme(themeMode = ThemeMode.LIGHT) {
        AppInfoSection("1.1.4")
    }
}
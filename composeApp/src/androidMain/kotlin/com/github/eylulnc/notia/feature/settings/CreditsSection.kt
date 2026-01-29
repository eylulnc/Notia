package com.github.eylulnc.notia.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.feature.settings.common.SettingsGroup
import com.github.eylulnc.notia.ui.common.ExternalLinkText

@Composable
fun CreditsSection() {
    SettingsGroup(title = stringResource(R.string.credits)) {
        ExternalLinkText(
            text = "People illustrations by ",
            linkText = "Storyset",
            url = "https://storyset.com/people"
        )
    }
}

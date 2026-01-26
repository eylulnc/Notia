package com.github.eylulnc.notia.ui.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.common.NotiaTopBar

@Composable
fun SettingsTopBar() {
    NotiaTopBar(
        title = stringResource(R.string.settings_title)
    )
}

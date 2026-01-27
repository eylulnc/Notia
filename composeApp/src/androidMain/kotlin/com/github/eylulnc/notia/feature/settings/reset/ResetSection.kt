package com.github.eylulnc.notia.feature.settings.reset

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.feature.settings.common.SettingsGroup
import com.github.eylulnc.notia.ui.theme.MutedRed

@Composable
fun ResetSection(onReset: () -> Unit) {
    SettingsGroup(title = stringResource(R.string.settings_reset)) {
        TextButton(onClick = onReset) {
            Text(
                text = stringResource(R.string.reset_all_data),
                color = MutedRed
            )
        }
    }
}

package com.github.eylulnc.notia.feature.settings.darkmode

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.feature.settings.common.SettingsGroup
import com.github.eylulnc.notia.ui.theme.NotiaTheme
import com.github.eylulnc.notia.ui.theme.ThemeMode

@Composable
fun AppearanceSection(
    selected: ThemeMode,
    onSelect: (ThemeMode) -> Unit
) {
    SettingsGroup(title = stringResource(R.string.settings_appearance)) {
        SegmentedControl(
            options = ThemeMode.entries,
            selected = selected,
            onSelect = onSelect
        )
    }
}

@Preview
@Composable
fun AppearanceSectionPreview() {
    NotiaTheme(themeMode = ThemeMode.LIGHT) {
        AppearanceSection(ThemeMode.LIGHT, {})
    }
}
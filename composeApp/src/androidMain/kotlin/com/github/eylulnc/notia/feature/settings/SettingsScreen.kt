package com.github.eylulnc.notia.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.feature.settings.darkmode.AppearanceSection
import com.github.eylulnc.notia.feature.settings.reset.ResetConfirmationDialog
import com.github.eylulnc.notia.feature.settings.reset.ResetSection
import com.github.eylulnc.notia.feature.settings.viewmodel.SettingsViewModel
import com.github.eylulnc.notia.ui.common.NotiaTopBar
import com.github.eylulnc.notia.ui.theme.BackgroundLight
import com.github.eylulnc.notia.ui.theme.Spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            NotiaTopBar(title = stringResource(R.string.settings_title))
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(Spacing.l)
        ) {
            AppearanceSection(
                selected = state.themeMode,
                onSelect = viewModel::onThemeSelected
            )

            Spacer(Modifier.height(Spacing.xl))

            ResetSection(
                onReset = viewModel::requestReset
            )

            Spacer(Modifier.weight(1f))

            AppInfoSection(appVersion = state.appVersion)
        }
    }

    if (state.isResetDialogVisible) {
        ResetConfirmationDialog(
            onConfirm = viewModel::confirmReset,
            onCancel = viewModel::cancelReset
        )
    }
}

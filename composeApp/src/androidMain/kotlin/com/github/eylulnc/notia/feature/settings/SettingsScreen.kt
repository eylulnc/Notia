package com.github.eylulnc.notia.feature.settings

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.feature.settings.darkmode.AppearanceSection
import com.github.eylulnc.notia.feature.settings.reminder.ReminderSection
import com.github.eylulnc.notia.feature.settings.reset.ResetConfirmationDialog
import com.github.eylulnc.notia.feature.settings.reset.ResetSection
import com.github.eylulnc.notia.feature.settings.viewmodel.SettingsViewModel
import com.github.eylulnc.notia.ui.common.NotiaTopBar
import com.github.eylulnc.notia.ui.theme.Spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    var pendingEnable by remember { mutableStateOf(false) }

    val notificationPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (pendingEnable && granted) {
                viewModel.onReminderEnabledChanged(true)
            } else {
                viewModel.onReminderEnabledChanged(false)
            }
        }

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
                .verticalScroll(rememberScrollState())

        ) {
            AppearanceSection(
                selected = state.themeMode,
                onSelect = viewModel::onThemeSelected
            )

            Spacer(Modifier.height(Spacing.xl))

            ReminderSection(
                isEnabled = state.isReminderEnabled,
                reminderTime = state.reminderTime,
                onEnabledChange = { enabled ->
                    if (!enabled) {
                        pendingEnable = false
                        viewModel.onReminderEnabledChanged(false)
                        return@ReminderSection
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        pendingEnable = true
                        notificationPermissionLauncher.launch(
                            Manifest.permission.POST_NOTIFICATIONS
                        )
                    } else {
                        viewModel.onReminderEnabledChanged(true)
                    }
                },
                onTimeChange = viewModel::onReminderTimeChanged
            )

            Spacer(Modifier.height(Spacing.xl))

            ResetSection(
                onReset = viewModel::requestReset
            )

            Spacer(Modifier.height(Spacing.xl))

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

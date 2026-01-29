package com.github.eylulnc.notia.feature.settings.reminder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.feature.settings.common.SettingsGroup
import com.github.eylulnc.notia.ui.theme.NotiaTheme
import com.github.eylulnc.notia.ui.theme.Spacing
import com.github.eylulnc.notia.ui.theme.ThemeMode
import kotlinx.datetime.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderSection(
    isEnabled: Boolean,
    reminderTime: LocalTime,
    onEnabledChange: (Boolean) -> Unit,
    onTimeChange: (LocalTime) -> Unit
) {
    var showTimePicker by remember { mutableStateOf(false) }

    SettingsGroup(title = stringResource(R.string.settings_reminder)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.settings_daily_reminder)
            )
            Switch(
                checked = isEnabled,
                onCheckedChange = onEnabledChange
            )
        }

        if (isEnabled) {
            HorizontalDivider(
                modifier = Modifier.padding(vertical = Spacing.l),
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showTimePicker = true },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(R.string.settings_reminder_time),
                    style = MaterialTheme.typography.bodyLarge
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(horizontal = Spacing.m, vertical = Spacing.s)
                ) {
                    Text(
                        text = formatTime(reminderTime),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }

    if (showTimePicker) {
        val timePickerState = rememberTimePickerState(
            initialHour = reminderTime.hour,
            initialMinute = reminderTime.minute,
            is24Hour = false
        )

        TimePickerDialog(
            onDismissRequest = { showTimePicker = false },
            onConfirm = {
                onTimeChange(LocalTime(timePickerState.hour, timePickerState.minute))
                showTimePicker = false
            }
        ) {
            TimePicker(state = timePickerState)
        }
    }
}

@Composable
fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        androidx.compose.material3.Surface(
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp
        ) {
            androidx.compose.foundation.layout.Column(
                modifier = Modifier.padding(Spacing.l),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
                Spacer(modifier = Modifier.height(Spacing.l))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    androidx.compose.material3.TextButton(onClick = onDismissRequest) {
                        Text(stringResource(R.string.cancel))
                    }
                    androidx.compose.material3.TextButton(onClick = onConfirm) {
                        Text("OK")
                    }
                }
            }
        }
    }
}

private fun formatTime(time: LocalTime): String {
    val hour = if (time.hour == 0 || time.hour == 12) 12 else time.hour % 12
    val amPm = if (time.hour < 12) "AM" else "PM"
    return "${hour.toString().padStart(2, '0')}:${time.minute.toString().padStart(2, '0')} $amPm"
}


@Preview
@Composable
fun ReminderSectionPreview() {
    NotiaTheme(ThemeMode.SYSTEM) {
        ReminderSection(
            isEnabled = true,
            reminderTime = LocalTime(12, 0),
            onEnabledChange = {},
            onTimeChange = {}
        )
    }
}
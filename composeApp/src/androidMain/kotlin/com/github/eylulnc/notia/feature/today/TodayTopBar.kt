package com.github.eylulnc.notia.feature.today

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.theme.Spacing
import com.github.eylulnc.notia.ui.common.NotiaTopBar
import com.github.eylulnc.notia.ui.common.StreakPill

@Composable
fun TodayTopBar(currentStreak: Int, onInfoClick: () -> Unit) {

    NotiaTopBar(
        title = stringResource(R.string.today_focus_title),
        leadingContent = {
            if (currentStreak > 0) {
                StreakPill(
                    count = currentStreak,
                    icon = Icons.Filled.LocalFireDepartment,
                    modifier = Modifier.padding(start = Spacing.m)
                )
            }
        },
        trailingContent = {
            IconButton(onClick = onInfoClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

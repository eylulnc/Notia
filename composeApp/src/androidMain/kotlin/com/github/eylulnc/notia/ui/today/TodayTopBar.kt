package com.github.eylulnc.notia.ui.today

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.Composable
import com.github.eylulnc.notia.ui.common.NotiaTopBar
import com.github.eylulnc.notia.ui.common.StreakPill

@Composable
fun TodayTopBar(currentStreak: Int) {

    NotiaTopBar(
        title = "Today’s Focus",
        leadingIcon = Icons.Outlined.WbSunny,
        trailingContent = {
            StreakPill(
                count = currentStreak,
                icon = Icons.Filled.LocalFireDepartment
            )
        }
    )
}

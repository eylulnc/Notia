package com.github.eylulnc.notia.ui.today

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.common.NotiaTopBar
import com.github.eylulnc.notia.ui.common.StreakPill

@Composable
fun TodayTopBar(currentStreak: Int) {

    NotiaTopBar(
        title = stringResource(R.string.today_focus_title),
        leadingIcon = Icons.Filled.WbSunny,
        trailingContent = {
            StreakPill(
                count = currentStreak,
                icon = Icons.Filled.LocalFireDepartment
            )
        }
    )
}

package com.github.eylulnc.notia.ui.main

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Today


enum class MainTab(
    val label: String,
    val icon: ImageVector
) {
    TODAY(
        label = "Today",
        icon = Icons.Outlined.Today
    ),
    HISTORY(
        label = "History",
        icon = Icons.Outlined.BarChart
    ),
    SETTINGS(
        label = "Settings",
        icon = Icons.Outlined.Settings
    )
}

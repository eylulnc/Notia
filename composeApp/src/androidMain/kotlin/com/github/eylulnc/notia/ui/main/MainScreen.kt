package com.github.eylulnc.notia.ui.main


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.eylulnc.notia.ui.history.HistoryScreen
import com.github.eylulnc.notia.ui.settings.SettingsScreen
import com.github.eylulnc.notia.ui.theme.BackgroundLight
import com.github.eylulnc.notia.ui.theme.Primary
import com.github.eylulnc.notia.ui.today.TodayScreen
import com.github.eylulnc.notia.ui.today.TodayViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(MainTab.TODAY) }

    Scaffold(
        containerColor = BackgroundLight,
        bottomBar = {
            NavigationBar(
                containerColor = BackgroundLight,
                tonalElevation = 0.dp
            ) {
                MainTab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label
                            )
                        },
                        label = {
                            Text(tab.label)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Primary,
                            selectedTextColor = Primary,
                            indicatorColor = Primary.copy(alpha = 0.1f),
                            unselectedIconColor = Primary.copy(alpha = 0.4f),
                            unselectedTextColor = Primary.copy(alpha = 0.4f)
                        )
                    )
                }
            }
        }
    ) { padding ->
        when (selectedTab) {
            MainTab.TODAY -> TodayScreen(
                modifier = Modifier.padding(padding)
            )
            MainTab.HISTORY -> HistoryScreen(
                modifier = Modifier.padding(padding)
            )
            MainTab.SETTINGS -> SettingsScreen(
                modifier = Modifier.padding(padding)
            )
        }
    }
}

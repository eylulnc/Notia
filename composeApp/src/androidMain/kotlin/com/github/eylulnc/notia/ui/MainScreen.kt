package com.github.eylulnc.notia.ui



import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.eylulnc.notia.ui.common.MainTab
import com.github.eylulnc.notia.ui.history.HistoryScreen
import com.github.eylulnc.notia.ui.history.HistoryTopBar
import com.github.eylulnc.notia.ui.settings.SettingsScreen
import com.github.eylulnc.notia.ui.settings.SettingsTopBar
import com.github.eylulnc.notia.ui.theme.BackgroundLight
import com.github.eylulnc.notia.ui.theme.Primary
import com.github.eylulnc.notia.ui.today.TodayScreen
import com.github.eylulnc.notia.ui.today.TodayTopBar
import com.github.eylulnc.notia.ui.today.TodayViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    todayViewModel: TodayViewModel = koinViewModel()
) {
    var selectedTab by remember { mutableStateOf(MainTab.TODAY) }

    val todayState by todayViewModel.uiState.collectAsState()

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            when (selectedTab) {
                MainTab.TODAY -> TodayTopBar(todayState.currentStreak)
                MainTab.HISTORY -> HistoryTopBar()
                MainTab.SETTINGS -> SettingsTopBar()
            }
        },
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
                modifier = Modifier.padding(padding),
                viewModel = todayViewModel
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
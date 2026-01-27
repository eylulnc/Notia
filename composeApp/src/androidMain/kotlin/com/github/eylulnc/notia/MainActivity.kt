package com.github.eylulnc.notia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.eylulnc.notia.feature.settings.repository.SettingsRepository
import com.github.eylulnc.notia.ui.MainScreen
import com.github.eylulnc.notia.ui.theme.NotiaTheme
import com.github.eylulnc.notia.ui.theme.ThemeMode
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val settingsRepository: SettingsRepository = koinInject()
            val themeMode by settingsRepository.themeMode
                .collectAsState(initial = ThemeMode.SYSTEM)

            NotiaTheme(themeMode = themeMode) {
                MainScreen()
            }
        }
    }
}
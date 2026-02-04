package com.github.eylulnc.notia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.github.eylulnc.notia.feature.onboarding.OnboardingScreen
import com.github.eylulnc.notia.feature.settings.repository.SettingsRepository
import com.github.eylulnc.notia.ui.MainScreen
import com.github.eylulnc.notia.ui.theme.NotiaTheme
import com.github.eylulnc.notia.ui.theme.ThemeMode
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val settingsRepository: SettingsRepository = koinInject()
            val scope = rememberCoroutineScope()

            val themeMode by settingsRepository.themeMode
                .collectAsState(initial = ThemeMode.SYSTEM)

            val isOnboardingSeen by settingsRepository.hasSeenOnboarding
                .collectAsState(initial = null)

            NotiaTheme(themeMode = themeMode) {
                when (isOnboardingSeen) {
                    null -> { }
                    false -> {
                        OnboardingScreen(
                            onFinish = {
                                scope.launch {
                                    settingsRepository.setOnboardingSeen()
                                }
                            }
                        )
                    }
                    true -> {
                        MainScreen()
                    }
                }
            }

        }
    }
}

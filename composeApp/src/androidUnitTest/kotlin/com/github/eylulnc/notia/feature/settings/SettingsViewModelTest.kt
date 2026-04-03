package com.github.eylulnc.notia.feature.settings

import com.github.eylulnc.notia.domain.model.DailyFocus
import com.github.eylulnc.notia.domain.repository.FocusRepository
import com.github.eylulnc.notia.feature.settings.repository.SettingsRepository
import com.github.eylulnc.notia.feature.settings.viewmodel.SettingsViewModel
import com.github.eylulnc.notia.ui.theme.ThemeMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var settingsRepository: FakeSettingsRepository
    private lateinit var focusRepository: FakeFocusRepository
    private lateinit var viewModel: SettingsViewModel
    private val appVersion = "1.0.0"

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        settingsRepository = FakeSettingsRepository()
        focusRepository = FakeFocusRepository()
        
        viewModel = SettingsViewModel(
            settingsRepository = settingsRepository,
            focusRepository = focusRepository,
            appVersion = appVersion
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state reflects app version`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcher.scheduler)) {
            viewModel.uiState.collect()
        }
        assertEquals(appVersion, viewModel.uiState.value.appVersion)
    }

    @Test
    fun `onThemeSelected updates repository`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcher.scheduler)) {
            viewModel.uiState.collect()
        }
        viewModel.onThemeSelected(ThemeMode.DARK)
        advanceUntilIdle()
        assertEquals(ThemeMode.DARK, settingsRepository.latestThemeMode)
    }

    @Test
    fun `requestReset updates state`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcher.scheduler)) {
            viewModel.uiState.collect()
        }
        viewModel.requestReset()
        assertTrue(viewModel.uiState.value.isResetDialogVisible)
    }

    @Test
    fun `cancelReset updates state`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcher.scheduler)) {
            viewModel.uiState.collect()
        }
        viewModel.requestReset()
        viewModel.cancelReset()
        assertFalse(viewModel.uiState.value.isResetDialogVisible)
    }

    @Test
    fun `confirmReset clears repository and hides dialog`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcher.scheduler)) {
            viewModel.uiState.collect()
        }
        viewModel.requestReset()
        viewModel.confirmReset()
        advanceUntilIdle()
        
        assertTrue(focusRepository.clearAllCalled)
        assertFalse(viewModel.uiState.value.isResetDialogVisible)
    }
}

private class FakeSettingsRepository : SettingsRepository {
    override val themeMode = MutableStateFlow(ThemeMode.SYSTEM)
    var latestThemeMode: ThemeMode? = null
    
    override suspend fun setThemeMode(mode: ThemeMode) {
        latestThemeMode = mode
        themeMode.value = mode
    }

    override val hasSeenOnboarding = MutableStateFlow(false)
    override suspend fun setOnboardingSeen() {}
}

private class FakeFocusRepository : FocusRepository {
    var clearAllCalled = false
    override fun getTodayFocus() = MutableStateFlow<DailyFocus?>(null)
    override suspend fun setTodayFocus(text: String) {}
    override suspend fun deleteTodayFocus() {}
    override fun getHistory() = MutableStateFlow<List<DailyFocus>>(emptyList())
    override fun getCurrentStreak() = MutableStateFlow(0)
    override fun getLongestStreak() = MutableStateFlow(0)
    override suspend fun clearAll() { clearAllCalled = true }
}

package com.github.eylulnc.notia.feature.today

import com.github.eylulnc.notia.domain.model.DailyFocus
import com.github.eylulnc.notia.domain.repository.FocusRepository
import com.github.eylulnc.notia.util.DateProvider
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
import kotlinx.datetime.LocalDate
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class TodayViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var focusRepository: FakeFocusRepository
    private lateinit var dateProvider: FakeDateProvider
    private lateinit var viewModel: TodayViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        focusRepository = FakeFocusRepository()
        dateProvider = FakeDateProvider()
        viewModel = TodayViewModel(focusRepository, dateProvider)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is loading`() = runTest {
        assertEquals(true, viewModel.uiState.value.isLoading)
    }

    @Test
    fun `state reflects repository data`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcher.scheduler)) {
            viewModel.uiState.collect()
        }

        val today = LocalDate(2025, 1, 1)
        dateProvider.setToday(today)
        focusRepository.setTodayFocusFlow(DailyFocus(today, "Test Focus"))
        focusRepository.setCurrentStreakFlow(5)

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals("Test Focus", state.focusText)
        assertEquals(5, state.currentStreak)
    }

    @Test
    fun `startEditing updates state`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcher.scheduler)) {
            viewModel.uiState.collect()
        }

        viewModel.startEditing()
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value.isEditing)
    }

    @Test
    fun `cancelEditing updates state`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcher.scheduler)) {
            viewModel.uiState.collect()
        }

        viewModel.startEditing()
        viewModel.cancelEditing()
        advanceUntilIdle()
        assertFalse(viewModel.uiState.value.isEditing)
    }

    @Test
    fun `saveFocus calls repository and stops editing`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcher.scheduler)) {
            viewModel.uiState.collect()
        }

        viewModel.startEditing()
        viewModel.saveFocus("New Focus")
        advanceUntilIdle()

        assertEquals("New Focus", focusRepository.savedFocus)
        assertFalse(viewModel.uiState.value.isEditing)
    }

    @Test
    fun `clearFocus calls repository`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcher.scheduler)) {
            viewModel.uiState.collect()
        }

        viewModel.clearFocus()
        advanceUntilIdle()
        assertTrue(focusRepository.deleteTodayFocusCalled)
    }
}

class FakeFocusRepository : FocusRepository {
    private val todayFocusFlow = MutableStateFlow<DailyFocus?>(null)
    private val historyFlow = MutableStateFlow<List<DailyFocus>>(emptyList())
    private val currentStreakFlow = MutableStateFlow(0)
    private val longestStreakFlow = MutableStateFlow(0)

    var savedFocus: String? = null
    var deleteTodayFocusCalled = false
    var clearAllCalled = false

    fun setTodayFocusFlow(focus: DailyFocus?) { todayFocusFlow.value = focus }
    fun setCurrentStreakFlow(streak: Int) { currentStreakFlow.value = streak }

    override fun getTodayFocus() = todayFocusFlow
    override fun getHistory() = historyFlow
    override fun getCurrentStreak() = currentStreakFlow
    override fun getLongestStreak() = longestStreakFlow

    override suspend fun setTodayFocus(text: String) { savedFocus = text }
    override suspend fun deleteTodayFocus() { deleteTodayFocusCalled = true }
    override suspend fun clearAll() { clearAllCalled = true }
}

class FakeDateProvider : DateProvider {
    private var todayDate = LocalDate(2025, 1, 1)
    fun setToday(date: LocalDate) { todayDate = date }
    override fun today(): LocalDate = todayDate
}

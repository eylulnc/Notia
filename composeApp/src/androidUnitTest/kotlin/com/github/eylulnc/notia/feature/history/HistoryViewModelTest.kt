package com.github.eylulnc.notia.feature.history

import com.github.eylulnc.notia.domain.model.DailyFocus
import com.github.eylulnc.notia.domain.repository.FocusRepository
import com.github.eylulnc.notia.feature.history.viewmodel.HistoryViewModel
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
class HistoryViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var focusRepository: FakeHistoryFocusRepository
    private lateinit var dateFormatter: HistoryDateFormatter
    private lateinit var viewModel: HistoryViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        focusRepository = FakeHistoryFocusRepository()
        dateFormatter = HistoryDateFormatter()
        viewModel = HistoryViewModel(focusRepository, dateFormatter)
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
    fun `state groups history by month`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcher.scheduler)) {
            viewModel.uiState.collect()
        }

        val focus1 = DailyFocus(LocalDate(2025, 1, 1), "Focus 1")
        val focus2 = DailyFocus(LocalDate(2025, 2, 1), "Focus 2")
        
        focusRepository.setHistory(listOf(focus2, focus1))
        
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(2, state.months.size)
        // Using contains to be safer with potential formatting variations
        assertTrue(state.months[0].monthLabel.contains("February"))
        assertTrue(state.months[1].monthLabel.contains("January"))
    }

    @Test
    fun `state reflects streaks`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcher.scheduler)) {
            viewModel.uiState.collect()
        }

        focusRepository.setCurrentStreak(3)
        focusRepository.setLongestStreak(10)
        
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(3, state.currentStreak)
        assertEquals(10, state.longestStreak)
    }
}

class FakeHistoryFocusRepository : FocusRepository {
    private val todayFocusFlow = MutableStateFlow<DailyFocus?>(null)
    private val historyFlow = MutableStateFlow<List<DailyFocus>>(emptyList())
    private val currentStreakFlow = MutableStateFlow(0)
    private val longestStreakFlow = MutableStateFlow(0)

    fun setHistory(history: List<DailyFocus>) { historyFlow.value = history }
    fun setCurrentStreak(streak: Int) { currentStreakFlow.value = streak }
    fun setLongestStreak(streak: Int) { longestStreakFlow.value = streak }

    override fun getTodayFocus() = todayFocusFlow
    override fun getHistory() = historyFlow
    override fun getCurrentStreak() = currentStreakFlow
    override fun getLongestStreak() = longestStreakFlow

    override suspend fun setTodayFocus(text: String) {}
    override suspend fun deleteTodayFocus() {}
    override suspend fun clearAll() {}
}

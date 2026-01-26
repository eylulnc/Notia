package com.github.eylulnc.notia.data

import com.github.eylulnc.notia.data.storage.FocusStorage
import com.github.eylulnc.notia.domain.model.DailyFocus
import com.github.eylulnc.notia.domain.repository.FocusRepository
import com.github.eylulnc.notia.util.DateProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

class FocusRepositoryImpl(
    private val storage: FocusStorage,
    private val dateProvider: DateProvider
) : FocusRepository {

    override fun getTodayFocus(): Flow<DailyFocus?> {
        return storage.observeAll().map { map ->
            val today = dateProvider.today()
            map[today]?.let { text ->
                DailyFocus(today, text)
            }
        }
    }

    override suspend fun setTodayFocus(text: String) {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) return

        val today = dateProvider.today()
        storage.save(today, trimmed)
    }

    override suspend fun deleteTodayFocus() {
        storage.delete(dateProvider.today())
    }

    override fun getHistory(): Flow<List<DailyFocus>> {
        return storage.observeAll().map { map ->
            val today = dateProvider.today()
            map
                .filterKeys { it != today }
                .map { (date, text) -> DailyFocus(date, text) }
                .sortedByDescending { it.date }
        }
    }

    override fun getCurrentStreak(): Flow<Int> {
        return storage.observeAll().map { map ->
            calculateCurrentStreak(map.keys)
        }
    }

    override fun getLongestStreak(): Flow<Int> {
        return storage.observeAll().map { map ->
            calculateLongestStreak(map.keys)
        }
    }

    // ---- private helpers ----

    private fun calculateCurrentStreak(dates: Set<LocalDate>): Int {
        var streak = 0
        var day = dateProvider.today()

        while (dates.contains(day)) {
            streak++
            day = day.minus(1, DateTimeUnit.DAY)
        }

        return streak
    }

    private fun calculateLongestStreak(dates: Set<LocalDate>): Int {
        if (dates.isEmpty()) return 0

        val sorted = dates.sorted()
        var longest = 1
        var current = 1

        for (i in 1 until sorted.size) {
            if (sorted[i] == sorted[i - 1].plus(1, DateTimeUnit.DAY)) {
                current++
                longest = maxOf(longest, current)
            } else {
                current = 1
            }
        }

        return longest
    }
}

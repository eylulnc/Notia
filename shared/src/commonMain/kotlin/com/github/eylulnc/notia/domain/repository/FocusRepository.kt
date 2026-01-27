package com.github.eylulnc.notia.domain.repository

import com.github.eylulnc.notia.domain.model.DailyFocus
import kotlinx.coroutines.flow.Flow

interface FocusRepository {

    fun getTodayFocus(): Flow<DailyFocus?>

    suspend fun setTodayFocus(text: String)

    suspend fun deleteTodayFocus()

    fun getHistory(): Flow<List<DailyFocus>>

    fun getCurrentStreak(): Flow<Int>

    fun getLongestStreak(): Flow<Int>

    suspend fun clearAll()
}

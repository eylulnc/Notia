package com.github.eylulnc.notia.domain.repository

import com.github.eylulnc.notia.domain.model.Goal
import kotlinx.coroutines.flow.Flow

interface GoalRepository {
    fun getGoal(): Flow<Goal?>
    suspend fun setGoal(text: String)
    suspend fun deleteGoal()
}

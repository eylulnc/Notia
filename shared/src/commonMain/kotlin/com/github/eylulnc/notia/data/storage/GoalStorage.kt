package com.github.eylulnc.notia.data.storage

import kotlinx.coroutines.flow.Flow

interface GoalStorage {
    fun observe(): Flow<String?>
    suspend fun save(text: String)
    suspend fun delete()
}

package com.github.eylulnc.notia.data.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface FocusStorage {
    suspend fun save(date: LocalDate, text: String)
    suspend fun delete(date: LocalDate)
    fun observeAll(): Flow<Map<LocalDate, String>>
}
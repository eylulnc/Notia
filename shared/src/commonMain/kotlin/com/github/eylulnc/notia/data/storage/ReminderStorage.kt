package com.github.eylulnc.notia.data.storage

import com.github.eylulnc.notia.domain.model.ReminderSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalTime

interface ReminderStorage {
    fun observe(): Flow<ReminderSettings>
    suspend fun enable(time: LocalTime)
    suspend fun disable()
}

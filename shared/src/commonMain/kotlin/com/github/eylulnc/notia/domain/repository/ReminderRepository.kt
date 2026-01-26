package com.github.eylulnc.notia.domain.repository

import com.github.eylulnc.notia.domain.model.ReminderSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalTime

interface ReminderRepository {
    fun getReminder(): Flow<ReminderSettings>
    suspend fun setReminder(time: LocalTime)
    suspend fun disableReminder()
}

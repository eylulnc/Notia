package com.github.eylulnc.notia.data

import com.github.eylulnc.notia.data.storage.ReminderStorage
import com.github.eylulnc.notia.domain.model.ReminderSettings
import com.github.eylulnc.notia.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalTime

class ReminderRepositoryImpl(
    private val storage: ReminderStorage
) : ReminderRepository {

    override fun getReminder(): Flow<ReminderSettings> =
        storage.observe()

    override suspend fun setReminder(time: LocalTime) {
        storage.enable(time)
    }

    override suspend fun disableReminder() {
        storage.disable()
    }
}

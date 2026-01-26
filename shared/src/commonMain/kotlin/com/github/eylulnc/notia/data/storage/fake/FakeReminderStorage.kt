package com.github.eylulnc.notia.data.storage.fake

import com.github.eylulnc.notia.data.storage.ReminderStorage
import com.github.eylulnc.notia.domain.model.ReminderSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalTime

class FakeReminderStorage : ReminderStorage {

    private val default = ReminderSettings(
        isEnabled = false,
        time = LocalTime(hour = 9, minute = 0)
    )

    private val state = MutableStateFlow(default)

    override fun observe(): Flow<ReminderSettings> = state

    override suspend fun enable(time: LocalTime) {
        state.value = ReminderSettings(
            isEnabled = true,
            time = time
        )
    }

    override suspend fun disable() {
        state.value = state.value.copy(isEnabled = false)
    }
}

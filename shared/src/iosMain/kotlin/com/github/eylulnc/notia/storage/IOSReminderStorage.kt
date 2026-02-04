package com.github.eylulnc.notia.storage

import com.github.eylulnc.notia.data.storage.ReminderStorage
import com.github.eylulnc.notia.domain.model.ReminderSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalTime
import platform.Foundation.NSUserDefaults
import platform.Foundation.setValue

private const val ENABLED = "notia.reminder.enabled"
private const val TIME = "notia.reminder.time"

class IOSReminderStorage : ReminderStorage {

    private val userDefaults = NSUserDefaults.standardUserDefaults
    private val _state = MutableStateFlow(loadSettings())

    override fun observe(): Flow<ReminderSettings> = _state

    override suspend fun enable(time: LocalTime) {
        userDefaults.setValue(true, forKey = ENABLED)
        userDefaults.setValue(time.toString(), forKey = TIME)
        _state.value = loadSettings()
    }

    override suspend fun disable() {
        userDefaults.setValue(false, forKey = ENABLED)
        _state.value = loadSettings()
    }

    private fun loadSettings(): ReminderSettings {
        val enabled = userDefaults.boolForKey(ENABLED)
        val timeString = userDefaults.stringForKey(TIME)
        val time = timeString?.let { runCatching { LocalTime.parse(it) }.getOrNull() } ?: LocalTime(9, 0)
        return ReminderSettings(isEnabled = enabled, time = time)
    }
}

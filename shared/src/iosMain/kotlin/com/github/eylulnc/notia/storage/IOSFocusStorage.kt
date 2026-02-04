package com.github.eylulnc.notia.storage

import com.github.eylulnc.notia.data.storage.FocusStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalDate
import platform.Foundation.NSUserDefaults
import platform.Foundation.setValue

class IOSFocusStorage : FocusStorage {

    private val userDefaults = NSUserDefaults.standardUserDefaults
    private val _state = MutableStateFlow<Map<LocalDate, String>>(emptyMap())

    init {
        reload()
    }

    override suspend fun save(date: LocalDate, text: String) {
        userDefaults.setValue(text, forKey = date.toString())
        reload()
    }

    override suspend fun delete(date: LocalDate) {
        userDefaults.removeObjectForKey(date.toString())
        reload()
    }

    override fun observeAll(): Flow<Map<LocalDate, String>> = _state

    override suspend fun clear() {
        val dictionary = userDefaults.dictionaryRepresentation()
        dictionary.keys.forEach { key ->
            if (key is String && runCatching { LocalDate.parse(key) }.isSuccess) {
                userDefaults.removeObjectForKey(key)
            }
        }
        reload()
    }

    private fun reload() {
        val map = mutableMapOf<LocalDate, String>()
        val dictionary = userDefaults.dictionaryRepresentation()
        for ((key, value) in dictionary) {
            if (key is String && value is String) {
                runCatching { LocalDate.parse(key) }.getOrNull()?.let { date ->
                    map[date] = value
                }
            }
        }
        _state.value = map
    }
}

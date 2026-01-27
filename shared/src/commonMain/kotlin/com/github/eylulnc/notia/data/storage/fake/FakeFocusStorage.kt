package com.github.eylulnc.notia.data.storage.fake

import com.github.eylulnc.notia.data.storage.FocusStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalDate

class FakeFocusStorage : FocusStorage {

    private val state = MutableStateFlow<Map<LocalDate, String>>(emptyMap())

    override suspend fun save(date: LocalDate, text: String) {
        state.value += (date to text)
    }

    override suspend fun delete(date: LocalDate) {
        state.value -= date
    }

    override fun observeAll(): Flow<Map<LocalDate, String>> = state

    override suspend fun clear() {
        state.value = emptyMap()
    }
}
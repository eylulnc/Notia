package com.github.eylulnc.notia.data.storage.fake

import com.github.eylulnc.notia.data.storage.GoalStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeGoalStorage : GoalStorage {

    private val state = MutableStateFlow<String?>(null)

    override fun observe(): Flow<String?> = state

    override suspend fun save(text: String) {
        state.value = text
    }

    override suspend fun delete() {
        state.value = null
    }
}

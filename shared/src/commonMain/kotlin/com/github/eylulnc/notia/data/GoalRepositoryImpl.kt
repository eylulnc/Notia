
package com.github.eylulnc.notia.data

import com.github.eylulnc.notia.data.storage.GoalStorage
import com.github.eylulnc.notia.domain.model.Goal
import com.github.eylulnc.notia.domain.repository.GoalRepository
import kotlinx.coroutines.flow.map

class GoalRepositoryImpl(
    private val storage: GoalStorage
) : GoalRepository {

    override fun getGoal() = storage.observe().map { text ->
        text?.let { Goal(it) }
    }

    override suspend fun setGoal(text: String) {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) return
        storage.save(trimmed)
    }

    override suspend fun deleteGoal() {
        storage.delete()
    }
}

package com.github.eylulnc.notia.data

import com.github.eylulnc.notia.data.storage.fake.FakeGoalStorage
import com.github.eylulnc.notia.domain.repository.GoalRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GoalRepositoryTest {

    @Test
    fun `no goal returns null`() = runTest {
        val repo = createRepo()
        assertNull(repo.getGoal().first())
    }

    @Test
    fun `setting goal trims whitespace`() = runTest {
        val repo = createRepo()

        repo.setGoal("  Learn KMP  ")

        val goal = repo.getGoal().first()
        assertEquals("Learn KMP", goal?.text)
    }

    @Test
    fun `blank goal is ignored`() = runTest {
        val repo = createRepo()

        repo.setGoal("   ")

        assertNull(repo.getGoal().first())
    }

    @Test
    fun `deleting goal clears it`() = runTest {
        val repo = createRepo()

        repo.setGoal("Consistency")
        repo.deleteGoal()

        assertNull(repo.getGoal().first())
    }

    private fun createRepo(): GoalRepository {
        return GoalRepositoryImpl(
            storage = FakeGoalStorage()
        )
    }
}

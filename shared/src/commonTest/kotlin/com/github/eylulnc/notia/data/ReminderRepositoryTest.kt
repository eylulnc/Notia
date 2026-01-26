package com.github.eylulnc.notia.data

import com.github.eylulnc.notia.data.storage.fake.FakeReminderStorage
import com.github.eylulnc.notia.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ReminderRepositoryTest {

    @Test
    fun `default reminder is disabled`() = runTest {
        val repo = createRepo()

        val reminder = repo.getReminder().first()

        assertFalse(reminder.isEnabled)
        assertEquals(LocalTime(9, 0), reminder.time)
    }

    @Test
    fun `setting reminder enables it with given time`() = runTest {
        val repo = createRepo()

        repo.setReminder(LocalTime(20, 30))

        val reminder = repo.getReminder().first()
        assertTrue(reminder.isEnabled)
        assertEquals(LocalTime(20, 30), reminder.time)
    }

    @Test
    fun `disabling reminder keeps last time`() = runTest {
        val repo = createRepo()

        repo.setReminder(LocalTime(18, 0))
        repo.disableReminder()

        val reminder = repo.getReminder().first()
        assertFalse(reminder.isEnabled)
        assertEquals(LocalTime(18, 0), reminder.time)
    }

    private fun createRepo(): ReminderRepository {
        return ReminderRepositoryImpl(
            storage = FakeReminderStorage()
        )
    }
}

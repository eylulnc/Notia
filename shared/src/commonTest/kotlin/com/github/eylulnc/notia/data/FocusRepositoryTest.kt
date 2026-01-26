package com.github.eylulnc.notia.data

import com.github.eylulnc.notia.data.storage.fake.FakeFocusStorage
import com.github.eylulnc.notia.domain.repository.FocusRepository
import com.github.eylulnc.notia.util.FakeDateProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlin.test.Test
import kotlin.test.assertEquals

class FocusRepositoryTest {

    private val startDate = LocalDate(2025, 1, 10)

    @Test
    fun `no entries gives streak zero`() = runTest {
        val repo = createRepo()
        assertEquals(0, repo.getCurrentStreak().first())
    }

    @Test
    fun `single day entry gives streak one`() = runTest {
        val repo = createRepo()
        repo.setTodayFocus("Focus")
        assertEquals(1, repo.getCurrentStreak().first())
    }

    @Test
    fun `missing today resets streak`() = runTest {
        val date = FakeDateProvider(startDate)
        val storage = FakeFocusStorage()
        val repo = FocusRepositoryImpl(storage, date)

        storage.save(startDate.minus(1, DateTimeUnit.DAY), "Yesterday")
        assertEquals(0, repo.getCurrentStreak().first())
    }

    @Test
    fun `consecutive days increase streak`() = runTest {
        val date = FakeDateProvider(startDate)
        val storage = FakeFocusStorage()
        val repo = FocusRepositoryImpl(storage, date)

        storage.save(startDate.minus(2, DateTimeUnit.DAY), "Day 1")
        storage.save(startDate.minus(1, DateTimeUnit.DAY), "Day 2")
        storage.save(startDate, "Day 3")

        assertEquals(3, repo.getCurrentStreak().first())
    }

    private fun createRepo(): FocusRepository {
        val date = FakeDateProvider(startDate)
        val storage = FakeFocusStorage()
        return FocusRepositoryImpl(storage, date)
    }
}

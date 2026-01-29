package com.github.eylulnc.notia.util

import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.test.Test
import kotlin.test.assertEquals

class SystemDateProviderTest {

    @Test
    fun `today returns current date from system clock`() {
        val provider = SystemDateProvider()
        val expected =  kotlin.time.Clock.System.todayIn(TimeZone.currentSystemDefault())
        val actual = provider.today()

        assertEquals(expected, actual)
    }
}

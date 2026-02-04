package com.github.eylulnc.notia.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HistoryDateFormatterTest {

    private lateinit var formatter: HistoryDateFormatter

    @BeforeTest
    fun setup() {
        formatter = HistoryDateFormatter()
    }

    @Test
    fun `format date returns short day and month with day of month`() {
        val date = LocalDate(2025, 1, 1) // Wednesday
        val result = formatter.format(date)
        
        // Note: Default locale might vary on machines, 
        // but typically in English it should be "Wed, Jan 1"
        assertEquals("Wed, Jan 1", result)
    }

    @Test
    fun `format year and month returns full month name and year`() {
        val result = formatter.format(2025, Month.JANUARY)
        assertEquals("January 2025", result)
    }
    
    @Test
    fun `format December returns full month name and year`() {
        val result = formatter.format(2024, Month.DECEMBER)
        assertEquals("December 2024", result)
    }
}

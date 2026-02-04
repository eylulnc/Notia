package com.github.eylulnc.notia.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

class HistoryDateFormatter {

    fun format(date: LocalDate): String {
        val dayOfWeek = date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)
        val month = date.month.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)
        return "$dayOfWeek, $month ${date.day}"
    }

    fun format(year: Int, month: Month): String {
        val monthName = month.name.lowercase().replaceFirstChar { it.uppercase() }
        return "$monthName $year"
    }
}

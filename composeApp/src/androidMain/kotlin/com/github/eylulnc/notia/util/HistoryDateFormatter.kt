package com.github.eylulnc.notia.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.toJavaLocalDate
import java.time.format.TextStyle
import java.util.Locale
import java.time.Month as JavaMonth

class HistoryDateFormatter {

    fun format(date: LocalDate): String {
        val javaDate = date.toJavaLocalDate()

        val dayOfWeek = javaDate.dayOfWeek.getDisplayName(
            TextStyle.SHORT,
            Locale.getDefault()
        )

        val month = javaDate.month.getDisplayName(
            TextStyle.SHORT,
            Locale.getDefault()
        )

        return "$dayOfWeek, $month ${javaDate.dayOfMonth}"
    }

    fun format(year: Int, month: Month): String {
        val javaMonth = JavaMonth.of(month.ordinal + 1)

        val monthName = javaMonth.getDisplayName(
            TextStyle.FULL,
            Locale.getDefault()
        )

        return "$monthName $year"
    }
}


package com.github.eylulnc.notia.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.TextStyle
import java.util.Locale

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
}


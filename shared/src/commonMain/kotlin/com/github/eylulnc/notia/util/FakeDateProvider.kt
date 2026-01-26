package com.github.eylulnc.notia.util

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

class FakeDateProvider(
    private var current: LocalDate
) : DateProvider {

    override fun today(): LocalDate = current

    fun advanceDays(days: Int) {
        current = current.plus(days, DateTimeUnit.DAY)
    }

    fun set(date: LocalDate) {
        current = date
    }
}

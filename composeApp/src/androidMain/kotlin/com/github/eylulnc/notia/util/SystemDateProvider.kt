package com.github.eylulnc.notia.util

import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.datetime.LocalDate

class SystemDateProvider : DateProvider {
    override fun today(): LocalDate =
        kotlin.time.Clock.System.todayIn(TimeZone.currentSystemDefault())
}

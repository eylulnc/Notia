package com.github.eylulnc.notia.util

import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.datetime.LocalDate
import kotlin.time.Clock

class SystemDateProvider : DateProvider {
    override fun today(): LocalDate =
        Clock.System.todayIn(TimeZone.currentSystemDefault())
}

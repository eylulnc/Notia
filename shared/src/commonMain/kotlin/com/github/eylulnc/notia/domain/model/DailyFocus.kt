package com.github.eylulnc.notia.domain.model

import kotlinx.datetime.LocalDate

data class DailyFocus(
    val date: LocalDate,
    val text: String
)
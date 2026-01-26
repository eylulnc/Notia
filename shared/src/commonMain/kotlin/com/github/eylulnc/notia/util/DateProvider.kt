package com.github.eylulnc.notia.util

import kotlinx.datetime.LocalDate

interface DateProvider {
    fun today(): LocalDate
}
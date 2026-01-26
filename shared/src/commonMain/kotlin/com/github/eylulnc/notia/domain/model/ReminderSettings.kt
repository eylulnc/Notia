package com.github.eylulnc.notia.domain.model

import kotlinx.datetime.LocalTime

data class ReminderSettings(
    val isEnabled: Boolean,
    val time: LocalTime
)

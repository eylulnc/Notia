package com.github.eylulnc.notia.feature.history.ui

import kotlinx.datetime.LocalDate

data class HistoryItem(
    val date: LocalDate,
    val dateLabel: String,   // e.g. "Mon, Jan 22"
    val focusText: String
)

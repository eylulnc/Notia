package com.github.eylulnc.notia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

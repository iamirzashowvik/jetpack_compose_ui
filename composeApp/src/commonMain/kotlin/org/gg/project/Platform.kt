package org.gg.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
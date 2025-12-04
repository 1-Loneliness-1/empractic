package org.example.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object TimeConverter {
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    private val zone = ZoneId.systemDefault()

    fun timestampToHhmmss(currentTime: Long): String {
        return Instant.ofEpochMilli(currentTime)
            .atZone(zone)
            .toLocalTime()
            .format(timeFormatter)
    }

}
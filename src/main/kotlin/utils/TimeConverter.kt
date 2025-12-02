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

    fun durationToHhmmssFormat(currentTime: Long): String {
        val totalSeconds = currentTime / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        return "%02d:%02d:%02d".format(hours, minutes, seconds)
    }

}
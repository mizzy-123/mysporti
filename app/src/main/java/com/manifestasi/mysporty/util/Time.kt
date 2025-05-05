package com.manifestasi.mysporty.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

object Time {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTimeAgo(createdAtString: String): String {
        return try {
            val parsedDate = OffsetDateTime.parse(createdAtString)
            val now = ZonedDateTime.now(ZoneId.systemDefault())

            val minutes = ChronoUnit.MINUTES.between(parsedDate, now)
            val hours = ChronoUnit.HOURS.between(parsedDate, now)
            val days = ChronoUnit.DAYS.between(parsedDate, now)

            when {
                minutes < 1 -> "Baru saja"
                minutes < 60 -> "$minutes menit lalu"
                hours < 24 -> "$hours jam lalu"
                else -> "$days hari lalu"
            }
        } catch (e: Exception) {
            "Tanggal tidak valid"
        }
    }
}
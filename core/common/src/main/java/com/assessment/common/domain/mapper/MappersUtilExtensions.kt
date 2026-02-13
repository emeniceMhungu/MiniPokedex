package com.assessment.common.domain.mapper

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.toLocalDate(): LocalDate {
    return LocalDate.ofEpochDay(this)
}
fun Instant.toReadableString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        .withZone(ZoneId.systemDefault()) // or ZoneId.of("Europe/London") etc.
    return formatter.format(this)
}
fun Instant.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(this, ZoneId.systemDefault())
}
fun Instant.toLocalDate(): LocalDate {
    return this.atZone(ZoneId.systemDefault()).toLocalDate()
}
fun Long.toInstant(): Instant {
    return Instant.ofEpochSecond(this)
}
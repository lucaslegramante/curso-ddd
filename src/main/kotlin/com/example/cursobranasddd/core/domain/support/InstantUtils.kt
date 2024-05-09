package com.example.cursobranasddd.core.domain

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit

object InstantUtils {
    fun now() = Instant.now().truncatedTo(ChronoUnit.MICROS)

    fun Instant.toLocalDate(): LocalDate {
        return LocalDateTime.ofInstant(this, ZoneOffset.UTC).toLocalDate()
    }
}

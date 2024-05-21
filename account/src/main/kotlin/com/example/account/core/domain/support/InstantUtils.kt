package com.example.account.core.domain.support

import java.time.Instant
import java.time.temporal.ChronoUnit

object InstantUtils {
    fun now() = Instant.now().truncatedTo(ChronoUnit.MICROS)
}

package com.hunhui.domain.performance

import java.time.LocalDateTime
import java.util.UUID

class PerformanceRound (
    val id: UUID,
    val performanceDateTime: LocalDateTime,
    val reservationStartDateTime: LocalDateTime,
    val reservationFinishDateTime: LocalDateTime,
) {
    val isReservationAvailable: Boolean
        get() = LocalDateTime.now().let {
            it.isBefore(reservationFinishDateTime) && it.isAfter(reservationStartDateTime)
        }
}

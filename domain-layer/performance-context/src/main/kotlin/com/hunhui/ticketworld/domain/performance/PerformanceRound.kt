package com.hunhui.ticketworld.domain.performance

import java.time.LocalDateTime
import java.util.UUID

class PerformanceRound(
    val id: UUID,
    val performanceDateTime: LocalDateTime,
    val reservationStartDateTime: LocalDateTime,
    val reservationFinishDateTime: LocalDateTime,
) {
    companion object {
        fun create(
            performanceDateTime: LocalDateTime,
            reservationStartDateTime: LocalDateTime,
            reservationFinishDateTime: LocalDateTime
        ) = PerformanceRound(
            id = UUID.randomUUID(),
            performanceDateTime = performanceDateTime,
            reservationStartDateTime = reservationStartDateTime,
            reservationFinishDateTime = reservationFinishDateTime
        )
    }

    val isReservationAvailable: Boolean
        get() = LocalDateTime.now().let {
            it.isBefore(reservationFinishDateTime) && it.isAfter(reservationStartDateTime)
        }
}

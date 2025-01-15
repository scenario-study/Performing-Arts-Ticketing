package com.hunhui.ticketworld.domain.performance

import com.hunhui.ticketworld.domain.performance.exception.InvalidPerformanceRoundException
import com.hunhui.ticketworld.domain.performance.exception.PerformanceRoundErrorCode
import java.time.LocalDateTime
import java.util.UUID

class PerformanceRound(
    val id: UUID,
    val performanceDateTime: LocalDateTime,
    val reservationStartDateTime: LocalDateTime,
    val reservationFinishDateTime: LocalDateTime,
) {
    init {
        require(reservationStartDateTime.isBefore(reservationFinishDateTime)) {
            throw InvalidPerformanceRoundException(
                PerformanceRoundErrorCode.RESERVATION_START_DATE_IS_AFTER_FINISH_DATE,
            )
        }
        require(reservationFinishDateTime.isBefore(performanceDateTime)) {
            throw InvalidPerformanceRoundException(
                PerformanceRoundErrorCode.RESERVATION_FINISH_DATE_IS_AFTER_PERFORMANCE_DATE,
            )
        }
    }

    companion object {
        fun create(
            performanceDateTime: LocalDateTime,
            reservationStartDateTime: LocalDateTime,
            reservationFinishDateTime: LocalDateTime,
        ) = PerformanceRound(
            id = UUID.randomUUID(),
            performanceDateTime = performanceDateTime,
            reservationStartDateTime = reservationStartDateTime,
            reservationFinishDateTime = reservationFinishDateTime,
        )
    }

    val isReservationAvailable: Boolean
        get() =
            LocalDateTime.now().let {
                it.isBefore(reservationFinishDateTime) && it.isAfter(reservationStartDateTime)
            }
}

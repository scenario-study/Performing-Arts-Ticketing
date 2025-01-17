package com.hunhui.ticketworld.domain.performance

import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.domain.performance.exception.PerformanceErrorCode.INVALID_RESERVATION_FINISH_DATE
import com.hunhui.ticketworld.domain.performance.exception.PerformanceErrorCode.INVALID_RESERVATION_START_DATE
import java.time.LocalDateTime
import java.util.UUID

class PerformanceRound(
    val id: UUID,
    val performanceDateTime: LocalDateTime,
    val reservationStartDateTime: LocalDateTime,
    val reservationFinishDateTime: LocalDateTime,
) {
    init {
        if (reservationStartDateTime.isAfter(reservationFinishDateTime)) throw BusinessException(INVALID_RESERVATION_START_DATE)
        if (reservationFinishDateTime.isAfter(performanceDateTime)) throw BusinessException(INVALID_RESERVATION_FINISH_DATE)
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

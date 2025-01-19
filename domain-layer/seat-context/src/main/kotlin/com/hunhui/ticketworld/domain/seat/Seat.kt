package com.hunhui.ticketworld.domain.seat

import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.domain.seat.exception.SeatErrorCode.POSITION_IS_NEGATIVE
import java.util.UUID

class Seat(
    val id: UUID,
    val performancePriceId: UUID,
    val seatName: String,
    val x: Int,
    val y: Int,
) {
    init {
        if (x < 0 || y < 0) throw BusinessException(POSITION_IS_NEGATIVE)
    }
}

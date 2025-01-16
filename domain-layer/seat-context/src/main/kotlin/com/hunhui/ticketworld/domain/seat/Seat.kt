package com.hunhui.ticketworld.domain.seat

import com.hunhui.ticketworld.domain.seat.exception.InvalidSeatException
import com.hunhui.ticketworld.domain.seat.exception.SeatErrorCode
import java.util.UUID

class Seat(
    val id: UUID,
    val gradeId: UUID,
    val seatName: String,
    val x: Int,
    val y: Int,
) {
    init {
        require(x >= 0 && y >= 0) { InvalidSeatException(SeatErrorCode.POSITION_NEGATIVE) }
    }
}

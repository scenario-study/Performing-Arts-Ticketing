package com.hunhui.ticketworld.domain.seat

import com.hunhui.ticketworld.domain.seat.exception.InvalidSeatAreaException
import com.hunhui.ticketworld.domain.seat.exception.SeatErrorCode
import java.util.UUID

class SeatArea(
    val id: UUID,
    val performanceId: UUID,
    val floorName: String,
    val areaName: String,
    val width: Int,
    val height: Int,
    val seats: List<Seat>,
) {
    init {
        require(width >= 0 && height >= 0) { throw InvalidSeatAreaException(SeatErrorCode.GRID_NEGATIVE) }
        require(seats.isNotEmpty()) { throw InvalidSeatAreaException(SeatErrorCode.SEAT_IS_EMPTY) }
        require(allSeatsContained) { throw InvalidSeatAreaException(SeatErrorCode.SEAT_NOT_CONTAINED) }
    }

    /**
     * 좌석이 영역의 너비와 높이에 포함되는지 확인
     * @return 좌석이 영역에 포함되면 true, 아니면 false
     */
    private fun Seat.isContained(): Boolean = this.x <= width && this.y <= height

    private val allSeatsContained: Boolean
        get() = seats.all { it.isContained() }
}

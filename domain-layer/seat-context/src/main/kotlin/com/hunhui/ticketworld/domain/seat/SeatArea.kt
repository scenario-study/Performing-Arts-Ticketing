package com.hunhui.ticketworld.domain.seat

import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.domain.seat.exception.SeatErrorCode.SEAT_IS_EMPTY
import com.hunhui.ticketworld.domain.seat.exception.SeatErrorCode.SEAT_NOT_CONTAINED
import com.hunhui.ticketworld.domain.seat.exception.SeatErrorCode.WIDTH_HEIGHT_IS_NOT_POSITIVE
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
        if (width <= 0 || height <= 0) throw BusinessException(WIDTH_HEIGHT_IS_NOT_POSITIVE)
        if (seats.isEmpty()) throw BusinessException(SEAT_IS_EMPTY)
        if (allSeatsContained.not()) throw BusinessException(SEAT_NOT_CONTAINED)
    }

    /**
     * 좌석이 영역의 너비와 높이에 포함되는지 확인
     * @return 좌석이 영역에 포함되면 true, 아니면 false
     */
    private fun Seat.isContained(): Boolean = this.x < width && this.y < height

    private val allSeatsContained: Boolean
        get() = seats.all { it.isContained() }
}

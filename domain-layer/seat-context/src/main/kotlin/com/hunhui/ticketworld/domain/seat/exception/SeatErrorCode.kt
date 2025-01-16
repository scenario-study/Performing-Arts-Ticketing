package com.hunhui.ticketworld.domain.seat.exception

import com.hunhui.ticketworld.common.error.ErrorCode

enum class SeatErrorCode(
    private val number: String,
    override val message: String,
) : ErrorCode {
    POSITION_NEGATIVE("001", "좌석의 좌표는 0 이상이어야 합니다."),
    GRID_NEGATIVE("002", "공간의 너비나 높이는 0 이상이어야 합니다."),
    SEAT_IS_EMPTY("003", "좌석은 비어있을 수 없습니다."),
    SEAT_NOT_CONTAINED("004", "좌석이 영역 내에 포함되어 있어야 합니다."),
    ;

    override val code: String
        get() = "$PREFIX$number"

    companion object {
        const val PREFIX = "SE"
    }
}

package com.hunhui.ticketworld.domain.seat.exception

import com.hunhui.ticketworld.common.error.ErrorCode

enum class SeatErrorCode(
    override val code: String,
    override val message: String,
) : ErrorCode {
    POSITION_IS_NEGATIVE("SE001", "좌석의 좌표는 0 이상이어야 합니다."),
    WIDTH_HEIGHT_IS_NOT_POSITIVE("SE002", "공간의 너비나 높이는 1 이상이어야 합니다."),
    SEAT_IS_EMPTY("SE003", "좌석은 비어있을 수 없습니다."),
    SEAT_NOT_CONTAINED("SE004", "좌석이 영역 내에 포함되어 있어야 합니다."),
    AREA_NOT_FOUND("SE005", "좌석 영역을 찾을 수 없습니다."),
}

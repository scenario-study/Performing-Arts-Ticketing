package com.hunhui.ticketworld.domain.seat.exception

import com.hunhui.ticketworld.common.error.ErrorCode

enum class SeatErrorCode(
    private val number: String,
    override val message: String,
) : ErrorCode {
    POSITION_NEGATIVE("001", "좌석의 좌표는 0 이상이어야 합니다."),
    ;

    override val code: String
        get() = "$PREFIX$number"

    companion object {
        const val PREFIX = "SE"
    }
}

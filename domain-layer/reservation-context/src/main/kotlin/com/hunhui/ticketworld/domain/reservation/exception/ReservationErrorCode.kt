package com.hunhui.ticketworld.domain.reservation.exception

import com.hunhui.ticketworld.common.error.ErrorCode

enum class ReservationErrorCode(
    override val code: String,
    override val message: String,
) : ErrorCode {
    CANNOT_RESERVE("RE001", "예매할 수 없는 좌석입니다."),
}

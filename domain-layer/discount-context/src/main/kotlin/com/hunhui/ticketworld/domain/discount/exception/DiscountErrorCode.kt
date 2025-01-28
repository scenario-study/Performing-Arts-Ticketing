package com.hunhui.ticketworld.domain.reservation.exception

import com.hunhui.ticketworld.common.error.ErrorCode

enum class DiscountErrorCode(
    override val code: String,
    override val message: String,
) : ErrorCode {
    INVALID_DISCOUNT_RATE("DI001", "할인율은 0이상 1이하의 실수입니다."),
}

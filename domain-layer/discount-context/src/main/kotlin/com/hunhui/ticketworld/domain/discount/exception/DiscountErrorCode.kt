package com.hunhui.ticketworld.domain.discount.exception

import com.hunhui.ticketworld.common.error.ErrorCode

enum class DiscountErrorCode(
    override val code: String,
    override val message: String,
) : ErrorCode {
    INVALID_DISCOUNT_RATE("DI001", "할인율은 0이상 1이하의 실수입니다."),
    CANNOT_DISCOUNT("DI002", "할인을 적용할 수 없습니다."),
    NOT_FOUND("DI003", "할인을 찾을 수 없습니다."),
}

package com.hunhui.ticketworld.domain.performance.exception

import com.hunhui.ticketworld.common.error.ErrorCode

enum class PerformanceErrorCode(
    override val code: String,
    override val message: String,
) : ErrorCode {
    PERFORMANCE_PRICE_IS_EMPTY("PE001", "공연의 가격은 적어도 하나 이상 존재해야 합니다."),
    ROUND_IS_EMPTY("PE002", "회차는 적어도 하나 이상 존재해야 합니다."),
    NOT_FOUND("PE003", "공연을 찾을 수 없습니다."),
    INVALID_RESERVATION_START_DATE("PE004", "예매 시작일은 예매 종료일 이전이여야 합니다."),
    INVALID_RESERVATION_FINISH_DATE("PE005", "예매 종료일은 공연일 이전이여야 합니다."),
}

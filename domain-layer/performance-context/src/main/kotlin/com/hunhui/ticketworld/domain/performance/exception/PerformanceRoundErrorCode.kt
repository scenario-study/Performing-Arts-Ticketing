package com.hunhui.ticketworld.domain.performance.exception

import com.hunhui.ticketworld.common.error.ErrorCode

enum class PerformanceRoundErrorCode(
    private val number: String,
    override val message: String,
) : ErrorCode {
    RESERVATION_START_DATE_IS_AFTER_FINISH_DATE("001", "예매 시작일은 예매 종료일 이전이여야 합니다."),
    RESERVATION_FINISH_DATE_IS_AFTER_PERFORMANCE_DATE("002", "예매 종료일은 공연일 이전이여야 합니다."),
    ;

    override val code: String
        get() = "$PREFIX$number"

    companion object {
        const val PREFIX = "PR"
    }
}

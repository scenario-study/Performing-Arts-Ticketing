package com.hunhui.ticketworld.domain.performance.exception

import com.hunhui.ticketworld.common.error.ErrorCode

enum class PerformanceErrorCode(
    private val number: String,
    override val message: String,
) : ErrorCode {
    SEAT_GRADE_IS_EMPTY("001", "좌석 등급은 비어있을 수 없습니다."),
    ROUND_IS_EMPTY("002", "회차는 비어있을 수 없습니다."),
    ;

    override val code: String
        get() = "$PREFIX$number"

    companion object {
        const val PREFIX = "PE"
    }
}

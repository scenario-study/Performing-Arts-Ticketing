package com.hunhui.ticketworld.domain.performance.exception

import com.hunhui.ticketworld.common.error.ErrorCode

enum class PerformanceErrorCode(
    private val number: String,
    override val message: String,
) : ErrorCode {
    TICKET_GRADE_IS_EMPTY("001", "좌석 등급은 비어있을 수 없습니다."),
    ROUND_IS_EMPTY("002", "회차는 비어있을 수 없습니다."),
    NOT_FOUND("003", "공연을 찾을 수 없습니다."),
    RESERVATION_START_DATE_IS_AFTER_FINISH_DATE("004", "예매 시작일은 예매 종료일 이전이여야 합니다."),
    RESERVATION_FINISH_DATE_IS_AFTER_PERFORMANCE_DATE("005", "예매 종료일은 공연일 이전이여야 합니다."),
    ;

    override val code: String
        get() = "$PREFIX$number"

    companion object {
        const val PREFIX = "PE"
    }
}

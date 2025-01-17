package com.hunhui.ticketworld.common.error

enum class GlobalErrorCode(
    override val code: String,
    override val message: String,
) : ErrorCode {
    INTERNAL_SERVER_ERROR("GL001", "서버 오류가 발생했습니다."),
    MONEY_IS_NEGATIVE("GL002", "금액은 0 이상이어야 합니다."),
}

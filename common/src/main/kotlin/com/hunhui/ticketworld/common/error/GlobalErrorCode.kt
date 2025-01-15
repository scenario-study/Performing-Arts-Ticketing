package com.hunhui.ticketworld.common.error

enum class GlobalErrorCode(
    private val number: String,
    override val message: String,
) : ErrorCode {
    INTERNAL_SERVER_ERROR("001", "서버 오류가 발생했습니다."),
    MONEY_IS_NEGATIVE("002", "금액은 0 이상이어야 합니다."),
    ;

    override val code: String
        get() = "$PREFIX$number"

    companion object {
        const val PREFIX = "GL"
    }
}

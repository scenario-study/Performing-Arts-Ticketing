package com.hunhui.ticketworld.domain.user.exception

import com.hunhui.ticketworld.common.error.ErrorCode

enum class UserErrorCode(
    override val code: String,
    override val message: String,
) : ErrorCode {
    NOT_FOUND("US001", "유저를 찾을 수 없습니다."),
}

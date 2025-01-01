package com.hunhui.common.error

import org.springframework.http.HttpStatus

enum class GlobalErrorCode(
    private val number: String,
    override val httpStatus: HttpStatus,
    override val message: String,
) : ErrorCode {
    INTERNAL_SERVER_ERROR("001", HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다."),
    INPUT_INVALID_VALUE("002", HttpStatus.BAD_REQUEST, "잘못된 입력입니다."),
    ACCESS_DENIED("003", HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    ;

    override val code: String
        get() = "$PREFIX$number"

    companion object {
        const val PREFIX = "GL"
    }
}

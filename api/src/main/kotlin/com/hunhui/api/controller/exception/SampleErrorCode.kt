package com.hunhui.api.controller.exception

import com.hunhui.common.error.ErrorCode
import org.springframework.http.HttpStatus

enum class SampleErrorCode(
    private val number: String,
    override val httpStatus: HttpStatus,
    override val message: String,
) : ErrorCode {
    SAMPLE_ERROR("001", HttpStatus.BAD_REQUEST, "샘플 에러가 발생했습니다."),
    ;

    override val code: String
        get() = "$PREFIX$number"

    companion object {
        const val PREFIX = "SM"
    }
}

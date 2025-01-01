package com.hunhui.common.error

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ErrorResponse private constructor(
    httpStatus: HttpStatus,
    body: ErrorResponseBody,
) : ResponseEntity<Any>(body, httpStatus) {
    companion object {
        fun from(code: ErrorCode): ErrorResponse =
            ErrorResponse(
                code.httpStatus,
                ErrorResponseBody(
                    code = code.code,
                    message = code.message,
                ),
            )
    }

    data class ErrorResponseBody(
        val code: String,
        val message: String,
    )
}

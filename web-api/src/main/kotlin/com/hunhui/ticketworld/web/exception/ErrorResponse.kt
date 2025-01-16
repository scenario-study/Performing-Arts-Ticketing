package com.hunhui.ticketworld.web.exception

import com.hunhui.ticketworld.common.error.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ErrorResponse(
    errorCode: ErrorCode,
    httpStatus: HttpStatus,
) : ResponseEntity<ErrorResponse.Body>(Body(errorCode), httpStatus) {
    class Body(
        errorCode: ErrorCode,
    ) {
        private val code: String = errorCode.code
        private val message: String = errorCode.message
    }
}

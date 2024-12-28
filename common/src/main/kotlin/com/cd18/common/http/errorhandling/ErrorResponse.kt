package com.cd18.common.http.errorhandling

import com.cd18.common.http.response.ApiStatus
import com.cd18.common.http.response.code.ErrorCode

data class ErrorResponse(
    val status: ApiStatus,
) {
    companion object {
        fun of(status: ErrorCode): ErrorResponse {
            return ErrorResponse(ApiStatus.of(status))
        }

        fun of(
            code: String,
            message: String,
        ): ErrorResponse {
            return ErrorResponse(ApiStatus.of(code, message))
        }
    }
}

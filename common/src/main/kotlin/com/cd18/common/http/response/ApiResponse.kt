package com.cd18.common.http.response

import com.cd18.common.http.response.code.BaseSuccessCode
import com.cd18.common.http.response.code.ErrorCode
import com.cd18.common.http.response.code.SuccessCode
import com.fasterxml.jackson.annotation.JsonInclude

data class ApiResponse<T>(
    val status: ApiStatus = ApiStatus.of(BaseSuccessCode.OK),
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val result: T? = null,
)

data class ApiStatus(
    val code: String,
    val message: String,
) {
    companion object {
        fun of(status: SuccessCode) = ApiStatus(code = status.code, message = status.message)

        fun of(status: ErrorCode) = ApiStatus(code = status.code, message = status.message)

        fun of(
            code: String,
            message: String,
        ) = ApiStatus(code = code, message = message)
    }
}

package com.hunhui.ticketworld.common.error

object AssertUtil {
    fun assertErrorCode(
        expectedErrorCode: ErrorCode,
        block: () -> Unit,
    ) {
        val exception =
            kotlin
                .runCatching { block() }
                .exceptionOrNull()
                ?: throw AssertionError("Expected BusinessException with error code ${expectedErrorCode.code}, but no exception was thrown")

        if (exception !is BusinessException) {
            throw AssertionError("Expected BusinessException, but got ${exception::class.simpleName}")
        }

        if (exception.errorCode != expectedErrorCode) {
            throw AssertionError(
                "Expected error code ${expectedErrorCode.code} (${expectedErrorCode.message}), " +
                    "but got \"[${exception.errorCode.code}] ${exception.errorCode.message}\"",
            )
        }
    }
}

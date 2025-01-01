package com.hunhui.common.error

open class BusinessException(
    val errorCode: ErrorCode,
) : RuntimeException(errorCode.message)

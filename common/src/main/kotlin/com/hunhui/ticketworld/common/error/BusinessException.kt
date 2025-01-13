package com.hunhui.ticketworld.common.error

open class BusinessException(
    val errorCode: ErrorCode,
) : RuntimeException(errorCode.message)

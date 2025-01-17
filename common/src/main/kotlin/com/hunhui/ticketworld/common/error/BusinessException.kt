package com.hunhui.ticketworld.common.error

class BusinessException(
    val errorCode: ErrorCode,
) : RuntimeException(errorCode.message) {
    override fun toString(): String = "[${errorCode.code}] ${errorCode.message}"
}

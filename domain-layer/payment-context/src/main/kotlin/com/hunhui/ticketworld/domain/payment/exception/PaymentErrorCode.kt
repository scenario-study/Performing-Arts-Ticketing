package com.hunhui.ticketworld.domain.payment.exception

import com.hunhui.ticketworld.common.error.ErrorCode

enum class PaymentErrorCode(
    override val code: String,
    override val message: String,
) : ErrorCode

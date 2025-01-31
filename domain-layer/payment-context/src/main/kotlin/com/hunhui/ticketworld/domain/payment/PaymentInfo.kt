package com.hunhui.ticketworld.domain.payment

import com.hunhui.ticketworld.common.vo.Money
import java.util.UUID

class PaymentInfo(
    val id: UUID,
    val performancePriceId: UUID,
    val reservationCount: Int,
    val discountId: UUID?,
    val paymentAmount: Money,
)

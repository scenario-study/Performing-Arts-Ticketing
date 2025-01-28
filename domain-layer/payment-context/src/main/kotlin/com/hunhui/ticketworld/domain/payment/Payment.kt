package com.hunhui.ticketworld.domain.payment

import com.hunhui.ticketworld.common.vo.Money
import java.util.UUID

class Payment(
    val id: UUID,
    val userId: UUID,
    val paymentMethod: PaymentMethod,
    val paymentInfos: List<PaymentInfo>,
) {
    val totalAmount: Money
        get() = Money(paymentInfos.sumOf { it.paymentAmount.amount })
}

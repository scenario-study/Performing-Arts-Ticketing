package com.hunhui.ticketworld.domain.performance

import com.hunhui.ticketworld.common.vo.Money
import java.util.UUID

class TicketGrade(
    val id: UUID,
    val gradeName: String,
    val price: Money,
) {
    companion object {
        fun create(
            gradeName: String,
            price: Long,
        ) = TicketGrade(
            id = UUID.randomUUID(),
            gradeName = gradeName,
            price = Money(price),
        )
    }
}

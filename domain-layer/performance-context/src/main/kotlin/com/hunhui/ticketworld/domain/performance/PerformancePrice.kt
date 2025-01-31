package com.hunhui.ticketworld.domain.performance

import com.hunhui.ticketworld.common.vo.Money
import java.util.UUID

class PerformancePrice(
    val id: UUID,
    val priceName: String,
    val price: Money,
) {
    companion object {
        fun create(
            priceName: String,
            price: Long,
        ) = PerformancePrice(
            id = UUID.randomUUID(),
            priceName = priceName,
            price = Money(price),
        )
    }
}

package com.hunhui.ticketworld.domain.discount

import java.util.UUID

interface DiscountRepository {
    fun findAllByPerformanceId(performanceId: UUID): List<Discount>

    fun save(discount: Discount)
}

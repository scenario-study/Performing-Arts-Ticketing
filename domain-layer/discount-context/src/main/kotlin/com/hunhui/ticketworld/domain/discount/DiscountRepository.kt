package com.hunhui.ticketworld.domain.discount

import java.util.UUID

interface DiscountRepository {
    fun getById(id: UUID): Discount

    fun findAllByIds(ids: List<UUID>): List<Discount>

    fun findAllByPerformanceId(performanceId: UUID): List<Discount>

    fun save(discount: Discount)
}

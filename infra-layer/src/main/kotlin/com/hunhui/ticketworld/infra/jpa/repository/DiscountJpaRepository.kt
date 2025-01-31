package com.hunhui.ticketworld.infra.jpa.repository

import com.hunhui.ticketworld.infra.jpa.entity.DiscountEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

internal interface DiscountJpaRepository : JpaRepository<DiscountEntity, UUID> {
    fun findAllByPerformanceId(performanceId: UUID): List<DiscountEntity>
}

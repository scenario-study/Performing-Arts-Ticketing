package com.performance.web.api.reservation.infrastructure

import com.performance.web.api.reservation.domain.Performance
import com.performance.web.api.reservation.domain.PerformanceRepository
import com.performance.web.api.reservation.infrastructure.jpa.PerformanceJpaRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class PerformanceRepositoryImpl(
    private val performanceJpaRepository: PerformanceJpaRepository
) : PerformanceRepository {

    override fun findById(id: Long): Optional<Performance> {
        return performanceJpaRepository.findById(id).map { it.toDomain() }
    }

    override fun findByPage(pageNum: Int): List<Performance> {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Performance> {
        return performanceJpaRepository.findAll().map { it.toDomain() }
    }

    override fun findByIdWithSeatClassAndDiscounts(id: Long): Optional<Performance> {
        return performanceJpaRepository.findPerformanceEntityWithSeatClassAndDiscountPolicies(id).map { it.toDomain() }
    }

    override fun findByIdWithSeatClass(id: Long): Optional<Performance> {
        return performanceJpaRepository.findPerformanceEntityByWithSeatClasses(id).map { it.toDomain() }
    }
}

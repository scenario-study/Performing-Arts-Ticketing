package com.performance.web.api.performance.service

import com.performance.web.api.common.domain.ResourceNotFoundException
import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.discount.domain.DiscountPolicyRepository
import com.performance.web.api.performance.domain.Performance
import com.performance.web.api.performance.domain.PerformanceRepository
import com.performance.web.api.performance.service.dto.PerformanceDiscountResponse
import org.springframework.stereotype.Service


@Service
class PerformanceService(
    private val performanceRepository: PerformanceRepository,
    private val discountPolicyRepository: DiscountPolicyRepository
) {


    fun findById(id: Long): Performance {
        return performanceRepository.findById(id)
            .orElseThrow { throw ResourceNotFoundException("$id 에 해당하는 Performance를 찾을 수 없습니다.") }
    }

    fun findAll(): List<Performance> {
        return performanceRepository.findAll()
    }


    fun findSeatClassByIdWithDiscounts(id: Long): List<PerformanceDiscountResponse> {
        val performance: Performance = performanceRepository.findByIdThrown(id)

        val discounts: List<DiscountPolicy> =
            discountPolicyRepository.findAllByPerformanceSeatClassIds(performance.getSeatClasses().map { it.getId() })

        val discountGroupByPerformanceIds: Map<Long, List<DiscountPolicy>> = discounts.groupBy {
            it.getPerformanceSeatClassId()
        }

        return discountGroupByPerformanceIds.toPerformanceDiscountResponse(performance)
    }

    private fun Map<Long, List<DiscountPolicy>>.toPerformanceDiscountResponse(performance: Performance):
        List<PerformanceDiscountResponse> {
        return this.keys.map {
            PerformanceDiscountResponse(
                performanceSeatClass = performance.findSeatClassById(it),
                discountPolies = this[it] ?: emptyList(),
            )
        }
    }
}

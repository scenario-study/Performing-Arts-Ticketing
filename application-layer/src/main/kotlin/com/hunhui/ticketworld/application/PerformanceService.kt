package com.hunhui.ticketworld.application

import com.hunhui.ticketworld.application.dto.request.PerformanceCreateRequest
import com.hunhui.ticketworld.application.dto.response.PerformanceResponse
import com.hunhui.ticketworld.application.dto.response.PerformanceSummaryListResponse
import com.hunhui.ticketworld.domain.performance.PerformanceRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PerformanceService(
    private val performanceRepository: PerformanceRepository,
) {
    fun getPerformance(performanceId: UUID): PerformanceResponse {
        val performance = performanceRepository.findById(performanceId)
        return performance?.let { PerformanceResponse.from(it) }
            ?: throw IllegalArgumentException("Performance not found")
    }

    fun getPerformances(
        page: Int,
        size: Int,
    ): PerformanceSummaryListResponse {
        val performances = performanceRepository.findAll(page, size)
        return PerformanceSummaryListResponse.from(performances)
    }

    fun createPerformance(performanceCreateRequest: PerformanceCreateRequest) {
        performanceRepository.save(performanceCreateRequest.toDomain())
    }
}

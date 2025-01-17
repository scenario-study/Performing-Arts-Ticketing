package com.hunhui.ticketworld.application

import com.hunhui.ticketworld.application.dto.request.PerformanceCreateRequest
import com.hunhui.ticketworld.application.dto.response.PerformanceResponse
import com.hunhui.ticketworld.application.dto.response.PerformanceSummaryListResponse
import com.hunhui.ticketworld.application.dto.response.SeatAreasResponse
import com.hunhui.ticketworld.domain.performance.PerformanceRepository
import com.hunhui.ticketworld.domain.seat.SeatArea
import com.hunhui.ticketworld.domain.seat.SeatAreaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class PerformanceService(
    private val performanceRepository: PerformanceRepository,
    private val seatAreaRepository: SeatAreaRepository,
) {
    fun getPerformance(performanceId: UUID): PerformanceResponse = PerformanceResponse.from(performanceRepository.getById(performanceId))

    fun getPerformances(
        page: Int,
        size: Int,
    ): PerformanceSummaryListResponse {
        val performances = performanceRepository.findAll(page, size)
        return PerformanceSummaryListResponse.from(performances)
    }

    @Transactional
    fun createPerformance(performanceCreateRequest: PerformanceCreateRequest) {
        val (performance, seatAreas) = performanceCreateRequest.toDomain()
        performanceRepository.save(performance)
        seatAreaRepository.saveAll(seatAreas)
    }

    fun getSeatAreas(performanceId: UUID): SeatAreasResponse {
        val seatAreas: List<SeatArea> = seatAreaRepository.findByPerformanceId(performanceId)
        return SeatAreasResponse.from(seatAreas)
    }
}

package com.performance.web.api.performance.controller.dto

import com.performance.web.api.performance.domain.Performance
import java.time.LocalDate

class PerformanceListApiResponse(
    val id: Long,
    val name: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
) {

    companion object {

        fun from(performance: Performance): PerformanceListApiResponse {
            return PerformanceListApiResponse(
                id = performance.getId(),
                name = performance.getName(),
                startDate = performance.getStartDate(),
                endDate = performance.getEndDate(),
            )
        }
    }
}

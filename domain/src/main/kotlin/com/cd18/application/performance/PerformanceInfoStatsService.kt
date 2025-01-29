package com.cd18.application.performance

import com.cd18.domain.metrics.dto.TargetCountSummaryDto
import java.time.LocalDate

interface PerformanceInfoStatsService {
    fun getPerformanceHourlyViewStats(
        date: LocalDate,
        hour: Int,
    ): List<TargetCountSummaryDto>

    fun savePerformanceHourlyViewStats(
        date: LocalDate,
        hour: Int,
        countSummary: List<TargetCountSummaryDto>,
    )
}

package com.cd18.domain.performance.repository

import com.cd18.domain.metrics.dto.TargetCountSummaryDto
import java.time.LocalDate

interface PerformanceInfoStatsRepository {
    fun savePerformanceHourlyViewStats(
        date: LocalDate,
        hour: Int,
        countSummary: List<TargetCountSummaryDto>,
    )
}

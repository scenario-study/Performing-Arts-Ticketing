package com.cd18.application.performance.impl

import com.cd18.application.performance.PerformanceInfoStatsService
import com.cd18.common.util.getEndOfHour
import com.cd18.common.util.getLocalDateTime
import com.cd18.domain.metrics.dto.TargetCountSummaryDto
import com.cd18.domain.metrics.enums.ActionType
import com.cd18.domain.metrics.enums.TargetType
import com.cd18.domain.metrics.repository.UserActionLogRepository
import com.cd18.domain.performance.repository.PerformanceInfoStatsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class PerformanceInfoStatsServiceImpl(
    private val userActionLogRepository: UserActionLogRepository,
    private val performanceInfoStatsRepository: PerformanceInfoStatsRepository,
) : PerformanceInfoStatsService {
    override fun getPerformanceHourlyViewStats(
        date: LocalDate,
        hour: Int,
    ): List<TargetCountSummaryDto> {
        val targetTime: LocalDateTime = getLocalDateTime(date, hour)
        return userActionLogRepository.getTargetIdCountSummary(
            actionType = ActionType.VIEW_PERF,
            targetType = TargetType.PERF,
            startTime = targetTime,
            endTime = targetTime.getEndOfHour(),
        )
    }

    @Transactional
    override fun savePerformanceHourlyViewStats(
        date: LocalDate,
        hour: Int,
        countSummary: List<TargetCountSummaryDto>,
    ) {
        performanceInfoStatsRepository.savePerformanceHourlyViewStats(
            date = date,
            hour = hour,
            countSummary = countSummary,
        )
    }
}

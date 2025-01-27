package com.cd18.batch.task

import com.cd18.application.performance.PerformanceInfoStatsService
import com.cd18.common.util.getDateAndHour
import com.cd18.domain.metrics.dto.TargetCountSummaryDto
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class PerformanceHourlyViewCountTask(
    private val performanceInfoStatsService: PerformanceInfoStatsService,
) : Tasklet {
    override fun execute(
        contribution: StepContribution,
        chunkContext: ChunkContext,
    ): RepeatStatus? {
        val (targetDate, targetHour) =
            LocalDateTime.parse(
                chunkContext.stepContext.jobParameters["targetTime"].toString(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            ).getDateAndHour()

        val data = readPerformanceHourlyViewStats(targetDate, targetHour)
        savePerformanceHourlyViewStats(targetDate, targetHour, data)

        return RepeatStatus.FINISHED
    }

    private fun readPerformanceHourlyViewStats(
        targetDate: LocalDate,
        targetHour: Int,
    ): List<TargetCountSummaryDto> {
        return performanceInfoStatsService.getPerformanceHourlyViewStats(
            date = targetDate,
            hour = targetHour,
        )
    }

    private fun savePerformanceHourlyViewStats(
        targetDate: LocalDate,
        targetHour: Int,
        data: List<TargetCountSummaryDto>,
    ) {
        performanceInfoStatsService.savePerformanceHourlyViewStats(
            date = targetDate,
            hour = targetHour,
            countSummary = data,
        )
    }
}

package com.hunhui.ticketworld.application.dto.response

import com.hunhui.ticketworld.domain.performance.Performance
import java.time.LocalDate
import java.util.UUID

data class PerformanceSummaryListResponse (
    val performances: List<PerformanceSummaryResponse>
) {
    companion object {
        fun from(performances: List<Performance>) = PerformanceSummaryListResponse(
            performances = performances.map { PerformanceSummaryResponse(
                id = it.id,
                title = it.title,
                performanceStartDate = it.startDate,
                performanceFinishDate = it.finishDate,
                minimumPrice = it.minimumPrice.amount,
                imageUrl = it.imageUrl,
                location = it.location
            ) }
        )
    }

    data class PerformanceSummaryResponse(
        val id: UUID,
        val title: String,
        val performanceStartDate: LocalDate,
        val performanceFinishDate: LocalDate,
        val minimumPrice: Long,
        val imageUrl: String,
        val location: String,
    )
}

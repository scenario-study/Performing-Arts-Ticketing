package com.hunhui.ticketworld.application.dto.response

import com.hunhui.ticketworld.domain.performance.Performance
import com.hunhui.ticketworld.domain.performance.PerformanceGenre
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class PerformanceResponse(
    val id: UUID,
    val title: String,
    val performanceStartDate: LocalDate,
    val performanceFinishDate: LocalDate,
    val genre: PerformanceGenre,
    val imageUrl: String,
    val location: String,
    val description: String,
    val minimumReservationStartDateTime: LocalDateTime,
    val ticketGrades: List<TicketGradeResponse>,
    val rounds: List<PerformanceRoundResponse>,
) {
    companion object {
        fun from(performance: Performance): PerformanceResponse =
            PerformanceResponse(
                id = performance.id,
                title = performance.title,
                performanceStartDate = performance.startDate,
                performanceFinishDate = performance.finishDate,
                genre = performance.genre,
                imageUrl = performance.imageUrl,
                location = performance.location,
                description = performance.description,
                minimumReservationStartDateTime = performance.minimumReservationStartDateTime,
                ticketGrades =
                    performance.ticketGrades.map {
                        TicketGradeResponse(
                            gradeName = it.gradeName,
                            price = it.price.amount,
                        )
                    },
                rounds =
                    performance.availableRounds.map {
                        PerformanceRoundResponse(
                            id = it.id,
                            performanceDateTime = it.performanceDateTime,
                        )
                    },
            )
    }

    data class TicketGradeResponse(
        val gradeName: String,
        val price: Long,
    )

    data class PerformanceRoundResponse(
        val id: UUID,
        val performanceDateTime: LocalDateTime,
    )
}

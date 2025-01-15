package com.hunhui.ticketworld.application.dto.response

import com.hunhui.ticketworld.domain.performance.Performance
import com.hunhui.ticketworld.domain.performance.PerformanceGenre
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class PerformanceResponse (
    val id: UUID,
    val title: String,
    val performanceStartDate: LocalDate,
    val performanceFinishDate: LocalDate,
    val genre: PerformanceGenre,
    val imageUrl: String,
    val location: String,
    val description: String,
    val seatGrades: List<SeatGradeResponse>,
    val rounds: List<PerformanceRoundResponse>,
) {
    companion object {
        fun from(performance: Performance) = PerformanceResponse(
            id = performance.id,
            title = performance.title,
            performanceStartDate = performance.startDate,
            performanceFinishDate = performance.finishDate,
            genre = performance.genre,
            imageUrl = performance.imageUrl,
            location = performance.location,
            description = performance.description,
            seatGrades = performance.seatGrades.map {
                SeatGradeResponse(
                    gradeName = it.gradeName,
                    price = it.price.amount
                )
            },
            rounds = performance.availableRounds.map {
                PerformanceRoundResponse(
                    id = it.id,
                    performanceDateTime = it.performanceDateTime
                )
            }
        )
    }

    data class SeatGradeResponse(
        val gradeName: String,
        val price: Long
    )

    data class PerformanceRoundResponse(
        val id: UUID,
        val performanceDateTime: LocalDateTime,
    )
}

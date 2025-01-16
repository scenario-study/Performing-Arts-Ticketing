package com.hunhui.ticketworld.application.dto.request

import com.hunhui.ticketworld.domain.performance.Performance
import com.hunhui.ticketworld.domain.performance.PerformanceGenre
import com.hunhui.ticketworld.domain.performance.PerformanceRound
import com.hunhui.ticketworld.domain.performance.TicketGrade
import java.time.LocalDateTime

data class PerformanceCreateRequest(
    val title: String,
    val genre: PerformanceGenre,
    val imageUrl: String,
    val location: String,
    val description: String,
    val ticketGrades: List<TicketGradeRequest>,
    val rounds: List<PerformanceRoundRequest>,
) {
    fun toDomain(): Performance =
        Performance.create(
            title = title,
            genre = genre,
            imageUrl = imageUrl,
            location = location,
            description = description,
            ticketGrades = ticketGrades.map { TicketGrade.create(it.gradeName, it.price) },
            rounds =
                rounds.map {
                    PerformanceRound.create(
                        it.performanceDateTime,
                        it.reservationStartDateTime,
                        it.reservationFinishDateTime,
                    )
                },
        )

    data class TicketGradeRequest(
        val gradeName: String,
        val price: Long,
    )

    data class PerformanceRoundRequest(
        val performanceDateTime: LocalDateTime,
        val reservationStartDateTime: LocalDateTime,
        val reservationFinishDateTime: LocalDateTime,
    )
}

package com.hunhui.ticketworld.domain.performance

import com.hunhui.ticketworld.common.vo.Money
import com.hunhui.ticketworld.domain.performance.exception.InvalidPerformanceException
import com.hunhui.ticketworld.domain.performance.exception.PerformanceErrorCode
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

class Performance(
    val id: UUID,
    val title: String,
    val genre: PerformanceGenre,
    val imageUrl: String,
    val location: String,
    val description: String,
    val ticketGrades: List<TicketGrade>,
    val rounds: List<PerformanceRound>,
) {
    init {
        require(ticketGrades.isNotEmpty()) { throw InvalidPerformanceException(PerformanceErrorCode.TICKET_GRADE_IS_EMPTY) }
        require(rounds.isNotEmpty()) { throw InvalidPerformanceException(PerformanceErrorCode.ROUND_IS_EMPTY) }
    }

    companion object {
        fun create(
            title: String,
            genre: PerformanceGenre,
            imageUrl: String,
            location: String,
            description: String,
            ticketGrades: List<TicketGrade>,
            rounds: List<PerformanceRound>,
        ): Performance =
            Performance(
                id = UUID.randomUUID(),
                title = title,
                genre = genre,
                imageUrl = imageUrl,
                location = location,
                description = description,
                ticketGrades = ticketGrades,
                rounds = rounds,
            )
    }

    val startDate: LocalDate
        get() = rounds.minOf { it.performanceDateTime.toLocalDate() }
    val finishDate: LocalDate
        get() = rounds.maxOf { it.performanceDateTime.toLocalDate() }
    val availableRounds: List<PerformanceRound>
        get() = rounds.filter { it.isReservationAvailable }
    val minimumReservationStartDateTime: LocalDateTime
        get() = rounds.minOf { it.reservationStartDateTime }
    val minimumPrice: Money
        get() = ticketGrades.minOf { it.price }
}

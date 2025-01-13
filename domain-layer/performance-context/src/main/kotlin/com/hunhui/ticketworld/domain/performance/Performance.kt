package com.hunhui.ticketworld.domain.performance

import java.time.LocalDate
import java.util.UUID

class Performance (
    val id: UUID,
    val title: String,
    val genre: PerformanceGenre,
    val imageUrl: String,
    val location: String,
    val description: String,
    val seatGrades: List<SeatGrade>,
    val status: PerformanceStatus,
    val rounds: List<PerformanceRound>,
) {
    companion object {
        fun create(
            title: String,
            genre: PerformanceGenre,
            imageUrl: String,
            location: String,
            description: String,
            seatGrades: List<SeatGrade>,
            rounds: List<PerformanceRound>,
        ): Performance {
            return Performance(
                id = UUID.randomUUID(),
                title = title,
                genre = genre,
                imageUrl = imageUrl,
                location = location,
                description = description,
                seatGrades = seatGrades,
                rounds = rounds,
                status = PerformanceStatus.PRIVATE,
            )
        }
    }

    val startDate: LocalDate
        get() = rounds.minOf { it.performanceDateTime.toLocalDate() }
    val finishDate: LocalDate
        get() = rounds.maxOf { it.performanceDateTime.toLocalDate() }
    val availableRounds: List<PerformanceRound>
        get() = rounds.filter { it.isReservationAvailable }
    val canView: Boolean
        get() = status.canView
    val canReserve: Boolean
        get() = status.canReserve
}

package com.hunhui.ticketworld.application.dto.request

import com.hunhui.ticketworld.domain.performance.Performance
import com.hunhui.ticketworld.domain.performance.PerformanceGenre
import com.hunhui.ticketworld.domain.performance.PerformanceRound
import com.hunhui.ticketworld.domain.performance.TicketGrade
import com.hunhui.ticketworld.domain.seat.Seat
import com.hunhui.ticketworld.domain.seat.SeatArea
import java.time.LocalDateTime
import java.util.UUID

data class PerformanceCreateRequest(
    val title: String,
    val genre: PerformanceGenre,
    val imageUrl: String,
    val location: String,
    val description: String,
    val ticketGrades: List<TicketGradeRequest>,
    val rounds: List<PerformanceRoundRequest>,
    val seatAreas: List<SeatAreaRequest>,
) {
    fun toDomain(): Pair<Performance, List<SeatArea>> {
        val performance = getPerformance()
        val seatAreas = getSeatAreas(performance.id, performance.ticketGrades)
        return performance to seatAreas
    }

    private fun getPerformance(): Performance =
        Performance.create(
            title = title,
            genre = genre,
            imageUrl = imageUrl,
            location = location,
            description = description,
            ticketGrades =
                ticketGrades.map {
                    TicketGrade.create(
                        gradeName = it.gradeName,
                        price = it.price,
                    )
                },
            rounds =
                rounds.map {
                    PerformanceRound.create(
                        it.performanceDateTime,
                        it.reservationStartDateTime,
                        it.reservationFinishDateTime,
                    )
                },
        )

    private fun getSeatAreas(
        performanceId: UUID,
        ticketGrades: List<TicketGrade>,
    ): List<SeatArea> =
        seatAreas.map {
            SeatArea(
                id = UUID.randomUUID(),
                performanceId = performanceId,
                floorName = it.floorName,
                areaName = it.areaName,
                width = it.width,
                height = it.height,
                seats =
                    it.seats.map { seat ->
                        Seat(
                            id = UUID.randomUUID(),
                            gradeId = ticketGrades[seat.gradeIndex].id,
                            seatName = seat.seatName,
                            x = seat.x,
                            y = seat.y,
                        )
                    },
            )
        }

    data class TicketGradeRequest(
        val gradeName: String,
        val price: Long,
    )

    data class PerformanceRoundRequest(
        val performanceDateTime: LocalDateTime,
        val reservationStartDateTime: LocalDateTime,
        val reservationFinishDateTime: LocalDateTime,
    )

    data class SeatAreaRequest(
        val floorName: String,
        val areaName: String,
        val width: Int,
        val height: Int,
        val seats: List<SeatRequest>,
    )

    data class SeatRequest(
        val gradeIndex: Int,
        val seatName: String,
        val x: Int,
        val y: Int,
    )
}

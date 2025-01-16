package com.hunhui.ticketworld.domain.seat

import java.util.UUID

object SeatFixtureFactory {
    fun createValidSeat(
        id: UUID = UUID.randomUUID(),
        gradeId: UUID = UUID.randomUUID(),
        seatName: String = "A1",
        x: Int = 1,
        y: Int = 1,
    ): Seat =
        Seat(
            id = id,
            gradeId = gradeId,
            seatName = seatName,
            x = x,
            y = y,
        )

    fun createValidSeatArea(
        id: UUID = UUID.randomUUID(),
        performanceId: UUID = UUID.randomUUID(),
        floorName: String = "1층",
        areaName: String = "A구역",
        width: Int = 10,
        height: Int = 10,
        seats: List<Seat> = listOf(createValidSeat(x = 1, y = 1), createValidSeat(x = 2, y = 2)),
    ): SeatArea =
        SeatArea(
            id = id,
            performanceId = performanceId,
            floorName = floorName,
            areaName = areaName,
            width = width,
            height = height,
            seats = seats,
        )
}

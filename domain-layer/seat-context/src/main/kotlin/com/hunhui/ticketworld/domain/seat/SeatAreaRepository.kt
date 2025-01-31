package com.hunhui.ticketworld.domain.seat

import java.util.UUID

interface SeatAreaRepository {
    fun findByPerformanceId(performanceId: UUID): List<SeatArea>

    fun saveAll(seatAreas: List<SeatArea>)
}

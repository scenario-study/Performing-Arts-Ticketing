package com.hunhui.ticketworld.domain.seat

import java.util.UUID

interface SeatAreaRepository {
    fun getById(id: UUID): SeatArea?

    fun findByPerformanceId(performanceId: UUID): List<SeatArea>

    fun save(seatArea: SeatArea)
}

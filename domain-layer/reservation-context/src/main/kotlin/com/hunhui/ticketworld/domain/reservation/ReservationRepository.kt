package com.hunhui.ticketworld.domain.reservation

import java.util.UUID

interface ReservationRepository {
    fun getById(id: UUID): Reservation

    fun findAllByRoundIdAndAreaId(
        performanceRoundId: UUID,
        seatAreaId: UUID,
    ): List<Reservation>

    fun save(reservation: Reservation)

    fun saveAll(reservations: List<Reservation>)
}

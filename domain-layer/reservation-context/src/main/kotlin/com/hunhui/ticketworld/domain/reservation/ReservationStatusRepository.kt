package com.hunhui.ticketworld.domain.reservation

import java.util.UUID

interface ReservationStatusRepository {
    fun getById(id: UUID): ReservationStatus

    fun findAllByRoundIdAndAreaId(
        performanceRoundId: UUID,
        seatAreaId: UUID,
    ): List<ReservationStatus>

    fun save(reservationStatus: ReservationStatus)

    fun saveAll(reservationStatuses: List<ReservationStatus>)
}

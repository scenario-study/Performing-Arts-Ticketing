package com.performance.web.api.mock

import com.performance.web.api.reservation.domain.Reservation
import com.performance.web.api.reservation.domain.ReservationRepository
import com.performance.web.api.reservation.domain.Ticket
import java.util.*

class FakeReservationRepository : ReservationRepository {

    private var autoIncrementId = 1L;
    private var autoIncrementTicketId = 1L;
    private val store = mutableMapOf<Long, Reservation>()

    override fun save(reservation: Reservation): Reservation {
        TODO()
    }

    override fun findById(reservationId: Long): Optional<Reservation> {
        return Optional.ofNullable(store[reservationId])
    }
}

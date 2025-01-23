package com.performance.web.api.reservation.domain

import java.util.Optional

interface ReservationRepository {

    fun save(reservation: Reservation): Reservation

    fun findById(reservationId: Long): Optional<Reservation>
}

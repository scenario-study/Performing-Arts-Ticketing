package com.performance.web.api.reservation.domain

interface ReservationRepository {

    fun save(reservation: Reservation): Reservation
}

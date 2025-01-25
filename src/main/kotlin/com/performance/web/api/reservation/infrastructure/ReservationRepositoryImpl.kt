package com.performance.web.api.reservation.infrastructure

import com.performance.web.api.reservation.domain.Reservation
import com.performance.web.api.reservation.domain.ReservationRepository
import org.springframework.stereotype.Component
import java.util.*


@Component
class ReservationRepositoryImpl : ReservationRepository {

    override fun save(reservation: Reservation): Reservation {
        TODO("Not yet implemented")
    }

    override fun findById(reservationId: Long): Optional<Reservation> {
        TODO("Not yet implemented")
    }

}

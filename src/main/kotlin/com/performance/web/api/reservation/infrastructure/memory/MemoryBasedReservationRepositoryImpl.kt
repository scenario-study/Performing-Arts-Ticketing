package com.performance.web.api.reservation.infrastructure.memory

import com.performance.web.api.reservation.domain.Reservation
import com.performance.web.api.reservation.domain.ReservationRepository
import com.performance.web.api.reservation.domain.Ticket
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Component
class MemoryBasedReservationRepositoryImpl : ReservationRepository {

    private val reservationKey = AtomicLong(0L)
    private val ticketKey = AtomicLong(0L)
    private val reservationStore = ConcurrentHashMap<Long, Reservation>()

    override fun save(reservation: Reservation): Reservation {
        val key = reservationKey.incrementAndGet()
        val reservation =
            Reservation(
                id = key,
                session = reservation.getSession(),
                customer = reservation.getCustomer(),
                tickets =
                    reservation.getTickets().map { ticket ->
                        Ticket(
                            id = ticketKey.incrementAndGet(),
                            totalAmount = ticket.getTotalAmount(),
                            regularPrice = ticket.getRegularPrice(),
                            seat = ticket.getSeat(),
                            appliedDiscountPolicy = ticket.getAppliedDiscountPolicy(),
                        )
                    },
            )
        reservationStore[key] = reservation
        return reservation
    }
}

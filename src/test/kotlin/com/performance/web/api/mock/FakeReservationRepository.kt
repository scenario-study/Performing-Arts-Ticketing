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
        if(reservation.getId() == 0L){
            val nextKey = autoIncrementId++;

            val newReservation = Reservation(
                id = nextKey,
                session = reservation.getSession(),
                customer = reservation.getCustomer(),
                tickets = reservation.getTickets().map {
                    if(it.getId() == 0L) {
                        val nextTicketKey = autoIncrementTicketId++;
                        Ticket(
                            id = nextTicketKey,
                            totalAmount = it.getTotalAmount(),
                            regularPrice = it.getRegularPrice(),
                            seat = it.getSeat(),
                            appliedDiscountPolicy = it.getAppliedDiscountPolicy()
                        )
                    }else{
                        it
                    }
                }
            )
            store[nextKey] = newReservation
            return newReservation
        }
        store[reservation.getId()] = reservation
        return reservation
    }

    override fun findById(reservationId: Long): Optional<Reservation> {
        return Optional.ofNullable(store[reservationId])
    }
}

package com.performance.web.api.reservation.domain

import com.performance.web.api.discount.domain.DiscountFactor
import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.performance.domain.Performance
import com.performance.web.api.seat.domain.Seat
import com.performance.web.api.seat.domain.SeatRepository
import com.performance.web.api.session.domain.Session
import org.springframework.stereotype.Component

@Component
class SeatReservation(
    private val seatRepository: SeatRepository,
) {

    fun reserve(
        performance: Performance,
        customer: Customer,
        session: Session,
        commands: List<SeatReserveCommand>,
        discountFactor: DiscountFactor
    ): Reservation {
        val tickets = mutableListOf<Ticket>()

        for ((seat, discountPolicy) in commands) {
            val ticket = seat.reserve(discountPolicy, discountFactor)
            seatRepository.save(seat)
            tickets.add(ticket)
        }

        return Reservation(
            sessionId = session.getId(),
            tickets = tickets,
            customer = customer,
            performanceSessionInfo = PerformanceSessionInfo.create(performance, session),
        )
    }

    data class SeatReserveCommand(
        val seat: Seat,
        val discountPolicy: DiscountPolicy,
    )

}

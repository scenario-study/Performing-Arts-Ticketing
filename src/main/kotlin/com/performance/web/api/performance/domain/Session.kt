package com.performance.web.api.performance.domain

import com.performance.web.api.performance.domain.discount.DiscountFactor
import com.performance.web.api.performance.domain.discount.DiscountPolicy
import java.time.LocalDateTime


class Session(
    performance: Performance,
    seats: List<Seat>
) {

    private val _performance: Performance = performance
    private val _seats: List<Seat> = seats


    fun reserve(command: ReserveCommand): Reservation {
        val tickets = mutableListOf<Ticket>()
        for (seatCommand in command.seatCommands) {
            val seat = findSeatById(seatCommand.seatId)
            val ticket = seat.reserve(seatCommand.discount, command.discountFactor)
            tickets.add(ticket)
        }

        return Reservation(this, command.customer, tickets)
    }


    private fun findSeatById(id: Long): Seat {
        return this._seats.find { it.getId() == id }
            ?: throw IllegalArgumentException("Seat with id $id not found")
    }


    data class ReserveCommand(
        val customer: Customer,
        val seatCommands: List<SeatReserveCommand>,
        val discountFactor: DiscountFactor
    )

    data class SeatReserveCommand(
        val seatId: Long,
        val discount: DiscountPolicy
    )
}

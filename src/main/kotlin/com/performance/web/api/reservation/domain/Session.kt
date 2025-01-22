package com.performance.web.api.reservation.domain

import com.performance.web.api.customer.domain.Customer
import com.performance.web.api.discount.domain.DiscountFactor
import com.performance.web.api.discount.domain.DiscountPolicy
import java.time.LocalDateTime


class Session(
    id: Long = 0L,
    performance: Performance,
    startDateTime : LocalDateTime,
    seats: List<Seat> = mutableListOf()
) {

    private val _id: Long = id
    private val _performance: Performance = performance
    private val _seats: List<Seat> = seats
    private val _startDateTime: LocalDateTime = startDateTime

    fun getId(): Long = _id
    fun getPerformance(): Performance = _performance
    fun getSeats(): List<Seat> = _seats
    fun getStartDateTime(): LocalDateTime = _startDateTime

    fun reserve(
        customer: Customer,
        discountFactor: DiscountFactor,
        seatCommands: List<SeatReserveCommand>
    ): Reservation {
        val tickets = mutableListOf<Ticket>()
        for (seatCommand in seatCommands) {
            val seat = findSeatById(seatCommand.seatId)
            val ticket = seat.reserve(
                discountPolicy = seatCommand.discountPolicy,
                discountFactor = discountFactor,
            )
            tickets.add(ticket)
        }

        return Reservation(
            session = this,
            customer = customer,
            tickets = tickets,
        )
    }


    private fun findSeatById(id: Long): Seat {
        return this._seats.find { it.getId() == id }
            ?: throw IllegalArgumentException("Seat with id $id not found")
    }

    data class SeatReserveCommand(
        val seatId: Long,
        val discountPolicy: DiscountPolicy
    )
}

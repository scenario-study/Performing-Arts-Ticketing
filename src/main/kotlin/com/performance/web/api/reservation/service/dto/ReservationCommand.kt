package com.performance.web.api.reservation.service.dto

import com.performance.web.api.customer.domain.Customer
import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.reservation.domain.Session

data class ReservationCommand(
    val customer: Customer,
    val sessionId: Long,
    val seatCommands: List<ReservationSeatCommand>,
) {

    data class ReservationSeatCommand(
        val seatId: Long,
        val discountPolicy: DiscountPolicy,
    ) {

        fun toEntityCommand(): Session.SeatReserveCommand =
            Session.SeatReserveCommand(
                seatId = seatId,
                discountPolicy = discountPolicy,
            )
    }
}

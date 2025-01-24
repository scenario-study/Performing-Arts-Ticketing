package com.performance.web.api.reservation.controller.dto

import com.performance.web.api.customer.domain.Customer
import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.reservation.service.dto.ReservationCommand

data class ReservationApiRequest(
    val customerId: Long,
    val sessionId: Long,
    val seatRequests: List<ReserveSeatApiRequest>,
) {

    data class ReserveSeatApiRequest(
        val seatId: Long,
        val discountPolicyId: Long?, // nullable
    )

    fun toServiceCommand(
        customer: Customer,
    ): ReservationCommand =
        ReservationCommand(
            customer = customer,
            sessionId = sessionId,
            seatCommands =
                seatRequests.map { request ->
                    ReservationCommand.ReservationSeatCommand(
                        seatId = request.seatId,
                        discountPolicyId = request.discountPolicyId
                    )
                },
        )
}

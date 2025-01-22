package com.performance.web.api.reservation.controller.dto

import com.performance.web.api.customer.domain.Customer
import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.reservation.service.dto.ReservationCommand

class ReservationApiRequest(
    val customerId: Long,
    val sessionId: Long,
    val seatRequests: List<ReserveSeatApiRequest>
) {

    data class ReserveSeatApiRequest(
        val seatId: Long,
        val discountPolicyId: Long? // nullable
    )


    fun getDiscountPolicyIds(): List<Long?> {
        return seatRequests.map { it.discountPolicyId }
    }

    fun toServiceCommand(customer: Customer, policies: List<DiscountPolicy>): ReservationCommand {
        return ReservationCommand(
            customer = customer,
            sessionId = sessionId,
            seatCommands = seatRequests.map { request ->
                val requestDiscountId = request.discountPolicyId ?: 0L
                ReservationCommand.ReservationSeatCommand(
                    seatId = request.seatId,
                    discountPolicy = policies.find { it.getId() == requestDiscountId }!!, // 절대 예외가 발생하지 않음.
                )
            },
        )
    }
}

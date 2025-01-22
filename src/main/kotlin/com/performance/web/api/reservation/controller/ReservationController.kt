package com.performance.web.api.reservation.controller

import com.performance.web.api.customer.service.CustomerService
import com.performance.web.api.discount.service.DiscountService
import com.performance.web.api.reservation.controller.dto.ReservationApiRequest
import com.performance.web.api.reservation.controller.dto.ReservationApiResponse
import com.performance.web.api.reservation.service.ReservationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reservations")
class ReservationController(
    private val reservationService: ReservationService,
    private val customerService: CustomerService,
    private val discountService: DiscountService,
) {

    @PostMapping("")
    fun createReservation(
        @RequestBody reservationApiRequest: ReservationApiRequest,
    ): ResponseEntity<ReservationApiResponse> {
        val customer = customerService.findById(reservationApiRequest.customerId)
        val discountPolicies = discountService.findAllByIdsOrDefault(reservationApiRequest.getDiscountPolicyIds())
        val result = reservationService.reserve(reservationApiRequest.toServiceCommand(customer, discountPolicies))
        return ResponseEntity.status(201)
            .body(ReservationApiResponse.from(result))
    }
}

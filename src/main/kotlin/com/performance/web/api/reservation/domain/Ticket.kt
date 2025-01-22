package com.performance.web.api.reservation.domain

import com.performance.web.api.common.domain.Money
import com.performance.web.api.discount.domain.DiscountPolicy

class Ticket(
    id: Long = 0L,
    totalAmount: Money,
    regularPrice: Money,
    seat: Seat,
    appliedDiscountPolicy: DiscountPolicy
) {

    private val _id: Long = id
    private val _totalAmount: Money = totalAmount
    private val _seat: Seat = seat
    private val _regularPrice: Money = regularPrice
    private val _appliedDiscountPolicy: DiscountPolicy = appliedDiscountPolicy

    fun getId(): Long = _id
    fun getTotalAmount(): Money = _totalAmount
    fun getRegularPrice(): Money = _regularPrice
    fun getSeat(): Seat = _seat
    fun getAppliedDiscountPolicy(): DiscountPolicy = _appliedDiscountPolicy
}

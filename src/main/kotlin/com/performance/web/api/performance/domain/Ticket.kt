package com.performance.web.api.performance.domain

import com.performance.web.api.common.domain.Money

class Ticket(
    totalAmount: Money,
    regularPrice : Money,
    seat: Seat,
) {

    private val _totalAmount: Money = totalAmount
    private val _seat: Seat = seat
    private val _regularPrice: Money = regularPrice


    fun getTotalAmount(): Money = _totalAmount
    fun getRegularPrice(): Money = _regularPrice
    fun getSeat(): Seat = _seat
}

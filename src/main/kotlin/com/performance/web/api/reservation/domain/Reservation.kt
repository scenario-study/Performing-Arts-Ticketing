package com.performance.web.api.reservation.domain

import com.performance.web.api.common.domain.Money
import com.performance.web.api.common.domain.sum

class Reservation(
    id: Long = 0L,
    session: Session,
    customer: Customer,
    tickets: List<Ticket> = mutableListOf(),
) {

    private val _id = id
    private val _session = session
    private val _customer = customer
    private val _tickets = tickets
    private val _totalAmount: Money = tickets.map { it.getTotalAmount() }.toList().sum()

    fun getId(): Long = _id

    fun getTickets(): List<Ticket> = _tickets

    fun getCustomer(): Customer = _customer

    fun getSession(): Session = _session

    fun getTotalAmount(): Money = _totalAmount
}

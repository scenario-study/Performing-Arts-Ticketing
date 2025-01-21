package com.performance.web.api.reservation.domain

import com.performance.web.api.common.domain.Money
import com.performance.web.api.common.domain.sum


class Reservation(
    session: Session,
    customer: Customer,
    tickets: List<Ticket>,
) {

    private val _session = session
    private val _customer = customer
    private val _tickets = tickets
    private val _totalAmount: Money = tickets.map { it.getTotalAmount() }.toList().sum()

    fun getTickets(): List<Ticket> = _tickets
    fun getCustomer(): Customer = _customer
    fun getSession(): Session = _session
    fun getTotalAmount(): Money = _totalAmount
}

package com.performance.web.api.performance.domain

import com.performance.web.api.common.domain.Money

class Reservation(
    session: Session,
    customer: Customer,
    tickets: List<Ticket>,
) {

    private val _session = session
    private val _customer = customer
    private val _tickets = tickets
    private val _totalAmount: Money = tickets.sumOf { it.getTotalAmount().longValue() }.let { Money.of(it) }

    fun getTickets(): List<Ticket> = _tickets
    fun getCustomer(): Customer = _customer
    fun getSession(): Session = _session
    fun getTotalAmount(): Money = _totalAmount
}

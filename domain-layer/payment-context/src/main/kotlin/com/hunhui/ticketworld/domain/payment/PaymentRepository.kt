package com.hunhui.ticketworld.domain.payment

interface PaymentRepository {
    fun save(payment: Payment)
}

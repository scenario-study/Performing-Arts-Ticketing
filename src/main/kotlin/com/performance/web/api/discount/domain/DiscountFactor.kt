package com.performance.web.api.discount.domain

import java.time.LocalDateTime

class DiscountFactor(
    val reserveDateTime: LocalDateTime,
    val ticketTotalAmount: Int,
) {
}

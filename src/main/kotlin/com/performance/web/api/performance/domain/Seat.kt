package com.performance.web.api.performance.domain

import com.performance.web.api.performance.domain.discount.Discount

class Seat(
    val id: Long,
    val name: String,
    val totalSeatCount: Int,
    val discounts: List<Discount>
) {
}

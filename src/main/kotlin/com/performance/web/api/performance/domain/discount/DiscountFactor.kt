package com.performance.web.api.performance.domain.discount

import com.performance.web.api.common.domain.Money
import java.time.LocalDateTime

class DiscountFactor(
    val reserveDateTime: LocalDateTime,
    val ticketTotalAmount: Int,
) {

}

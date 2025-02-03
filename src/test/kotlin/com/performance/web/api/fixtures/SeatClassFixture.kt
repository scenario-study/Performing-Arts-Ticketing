package com.performance.web.api.fixtures

import com.performance.web.api.common.domain.Money
import com.performance.web.api.seat.domain.SeatClass

class SeatClassFixture {

    companion object {

        fun create(
            price: Money = Money.of(10000),
            classType: String = "VIP",
        ): SeatClass =
            SeatClass(
                price = price,
                classType = classType,
            )
    }
}

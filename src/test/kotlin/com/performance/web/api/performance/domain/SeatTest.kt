package com.performance.web.api.performance.domain

import com.performance.web.api.common.domain.Money
import com.performance.web.api.performance.domain.discount.DiscountFactor
import com.performance.web.api.performance.domain.discount.NoneDiscountPolicy
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class SeatTest{


    @Test
    fun `이미 예약된 좌석은 예매할 수 없다`() {
        //given
        val seat = Seat(
            seatClass = SeatClass(
                price = Money.of(10000)
            ),
            seatStatus = SeatStatus.RESERVED
        )

        //when
        //then
        assertThatThrownBy {
            seat.reserve(NoneDiscountPolicy(), discountFactor = DiscountFactor(
                reserveDateTime = LocalDateTime.of(2021, 1, 1, 0, 0),
                ticketTotalAmount = 1,
            ))
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

}

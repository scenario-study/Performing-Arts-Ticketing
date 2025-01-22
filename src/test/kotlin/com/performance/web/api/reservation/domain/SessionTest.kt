package com.performance.web.api.reservation.domain

import com.performance.web.api.common.domain.Money
import com.performance.web.api.customer.domain.Customer
import com.performance.web.api.discount.domain.DateRangeCondition
import com.performance.web.api.discount.domain.DiscountFactor
import com.performance.web.api.discount.domain.PercentDiscountPolicy
import com.performance.web.api.fixtures.*
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class SessionTest {

    @Test
    fun `비율할인 금액을 적용한 후 할인된 가격으로 예매를 생성할 수 있다`() {
        // given
        val seat = SeatFixture.create(1L, seatClass = SeatClassFixture.create(price = Money.of(10000)))
        val session =
            SessionFixture.create(
                seats = listOf(seat),
            )
        val discountPolicy =
            PercentDiscountPolicy(
                percent = 0.3,
                name = "2025년 새해 기념 30% 할인 ",
                conditions =
                    arrayOf(
                        DateRangeCondition(
                            startDateTime = LocalDateTime.of(2025, 1, 1, 0, 0),
                            endDateTime = LocalDateTime.of(2025, 1, 10, 0, 0),
                        ),
                    ),
            )

        // when
        val reservation: Reservation =
            session.reserve(
                customer = Customer(1L, "김철수"),
                seatCommands =
                    listOf(
                        Session.SeatReserveCommand(
                            seatId = 1L,
                            discountPolicy,
                        ),
                    ),
                discountFactor =
                    DiscountFactor(
                        reserveDateTime = LocalDateTime.of(2025, 1, 1, 0, 0),
                        ticketTotalAmount = 1,
                    ),
            )

        // then
        assertThat(reservation.getTotalAmount()).isEqualTo(Money.of(7000))
        assertThat(reservation.getTickets().size).isEqualTo(1)
    }

    @Test
    fun `여러 티켓을 한번에 예매를 생성할 수 있다`() {
        // given
        val session =
            SessionFixture.create(
                seats =
                    listOf(
                        SeatFixture.create(
                            id = 1L,
                            seatClass = SeatClassFixture.create(Money.of(10000)),
                        ),
                        SeatFixture.create(
                            id = 2L,
                            seatClass = SeatClassFixture.create(Money.of(10000)),
                        ),
                    ),
            )

        val discountPolicy =
            PercentDiscountPolicy(
                percent = 0.3,
                name = "2025년 새해 기념 30% 할인 ",
                conditions =
                    arrayOf(
                        DateRangeCondition(
                            startDateTime = LocalDateTime.of(2025, 1, 1, 0, 0),
                            endDateTime = LocalDateTime.of(2025, 1, 10, 0, 0),
                        ),
                    ),
            )

        // when
        val reservation: Reservation =
            session.reserve(
                customer = Customer(1L, "김철수"),
                seatCommands =
                    listOf(
                        Session.SeatReserveCommand(
                            seatId = 1L,
                            discountPolicy,
                        ),
                        Session.SeatReserveCommand(
                            seatId = 2L,
                            discountPolicy,
                        ),
                    ),
                discountFactor =
                    DiscountFactor(
                        reserveDateTime = LocalDateTime.of(2025, 1, 1, 0, 0),
                        ticketTotalAmount = 1,
                    ),
            )

        // then
        assertThat(reservation.getTotalAmount()).isEqualTo(Money.of(14000))
        assertThat(reservation.getTickets().size).isEqualTo(2)
    }

    @Test
    fun `없는 좌석에 예매를 요청하면 예외를 반환한다`() {
        // given
        val session =
            SessionFixture.create(
                seats =
                    listOf(
                        SeatFixture.create(
                            id = 1L,
                            seatClass = SeatClassFixture.create(Money.of(10000)),
                        ),
                        SeatFixture.create(
                            id = 2L,
                            seatClass = SeatClassFixture.create(Money.of(10000)),
                        ),
                    ),
            )

        val discountPolicy = DiscountPolicyFixture.createPercent()

        // when
        // then
        Assertions.assertThatThrownBy {
            session.reserve(
                customer = Customer(1L, "김철수"),
                seatCommands =
                    listOf(
                        Session.SeatReserveCommand(
                            seatId = 3L,
                            discountPolicy,
                        ),
                    ),
                discountFactor =
                    DiscountFactor(
                        reserveDateTime = LocalDateTime.of(2025, 1, 1, 0, 0),
                        ticketTotalAmount = 1,
                    ),
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }
}

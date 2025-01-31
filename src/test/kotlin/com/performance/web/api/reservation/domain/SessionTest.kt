package com.performance.web.api.reservation.domain

import com.performance.web.api.common.domain.Money
import com.performance.web.api.member.domain.Member
import com.performance.web.api.discount.domain.DateRangeCondition
import com.performance.web.api.discount.domain.DiscountFactor
import com.performance.web.api.discount.domain.PercentDiscountPolicy
import com.performance.web.api.fixtures.*
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class SessionTest {

    @Test
    fun `비율할인 금액을 적용한 후 할인된 가격으로 예매를 생성할 수 있다`() {
        // given
        val discountPolicy =
            PercentDiscountPolicy(
                id = 1L,
                percent = 0.3,
                name = "2025년 새해 기념 30% 할인 ",
                conditions =
                arrayOf(
                    DateRangeCondition(
                        startDate = LocalDate.of(2025, 1, 1),
                        endDate = LocalDate.of(2025, 1, 10),
                    ),
                ),
            )
        val seat = SeatFixture.create(
            id = 1L,
            seatClass = SeatClassFixture.create(
                price = Money.of(10000),
                discountPolicies = listOf(discountPolicy),
            ),
        )
        val session =
            SessionFixture.create(
                seats = listOf(seat),
            )


        // when
        val reservation: Reservation =
            session.reserve(
                customer = Customer(1L),
                seatCommands =
                listOf(
                    Session.SeatReserveCommand(
                        seatId = 1L,
                        discountPolicyId = 1L,
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
        val discountPolicy =
            PercentDiscountPolicy(
                id = 1L,
                percent = 0.3,
                name = "2025년 새해 기념 30% 할인 ",
                conditions =
                arrayOf(
                    DateRangeCondition(
                        startDate = LocalDate.of(2025, 1, 1),
                        endDate = LocalDate.of(2025, 1, 10),
                    ),
                ),
            )

        val session =
            SessionFixture.create(
                seats =
                listOf(
                    SeatFixture.create(
                        id = 1L,
                        seatClass = SeatClassFixture.create(
                            price = Money.of(10000),
                            discountPolicies =
                            listOf(discountPolicy),
                        ),
                    ),
                    SeatFixture.create(
                        id = 2L,
                        seatClass = SeatClassFixture.create(
                            price = Money.of(10000),
                            discountPolicies =
                            listOf(discountPolicy),
                        ),
                    ),
                ),
            )


        // when
        val reservation: Reservation =
            session.reserve(
                customer = Customer(1L),
                seatCommands =
                listOf(
                    Session.SeatReserveCommand(
                        seatId = 1L,
                        discountPolicyId = 1L,
                    ),
                    Session.SeatReserveCommand(
                        seatId = 2L,
                        discountPolicyId = 1L,
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

        // when
        // then
        Assertions.assertThatThrownBy {
            session.reserve(
                customer = Customer(1L),
                seatCommands =
                listOf(
                    Session.SeatReserveCommand(
                        seatId = 3L,
                        discountPolicyId = 1L,
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

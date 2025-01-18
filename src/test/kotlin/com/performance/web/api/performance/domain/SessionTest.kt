package com.performance.web.api.performance.domain

import com.performance.web.api.common.domain.Money
import com.performance.web.api.performance.domain.discount.DateRangeCondition
import com.performance.web.api.performance.domain.discount.DiscountFactor
import com.performance.web.api.performance.domain.discount.PercentDiscountPolicy
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class SessionTest {


    @Test
    fun `비율할인 금액을 적용한 후 할인된 가격으로 예매를 생성할 수 있다`() {
        //given
        val seat = seatFixtures(1L, Money.of(10000))
        val performance = Performance(name = "공연공연")
        val session = Session(
            performance = performance,
            seats = listOf(seat),
        )
        val discountPolicy = PercentDiscountPolicy(
            percent = 0.3,
            name = "2025년 새해 기념 30% 할인 ",
            conditions = arrayOf(
                DateRangeCondition(
                    startDateTime = LocalDateTime.of(2025, 1, 1, 0, 0),
                    endDateTime = LocalDateTime.of(2025, 1, 10, 0, 0),
                ),
            ),
        )

        // when

        val reservation: Reservation = session.reserve(
            Session.ReserveCommand(
                customer = Customer("김철수"),
                seatCommands = listOf(
                    Session.SeatReserveCommand(
                        seatId = 1L,
                        discountPolicy,
                    ),
                ),
                discountFactor = DiscountFactor(
                    reserveDateTime = LocalDateTime.of(2025, 1, 1, 0, 0),
                    ticketTotalAmount = 1,
                ),
            ),
        )

        //then
        assertThat(reservation.getTotalAmount()).isEqualTo(Money.of(7000))
        assertThat(reservation.getTickets().size).isEqualTo(1)
    }


    @Test
    fun `여러 티켓을 한번에 예매를 생성할 수 있다`() {
        //given
        val performance = Performance(name = "공연공연")
        val session = Session(
            performance = performance,
            seats = listOf(seatFixtures(1L, Money.of(10000)), seatFixtures(2L, Money.of(10000))),
        )
        val discountPolicy = PercentDiscountPolicy(
            percent = 0.3,
            name = "2025년 새해 기념 30% 할인 ",
            conditions = arrayOf(
                DateRangeCondition(
                    startDateTime = LocalDateTime.of(2025, 1, 1, 0, 0),
                    endDateTime = LocalDateTime.of(2025, 1, 10, 0, 0),
                ),
            ),
        )

        // when

        val reservation: Reservation = session.reserve(
            Session.ReserveCommand(
                customer = Customer("김철수"),
                seatCommands = listOf(
                    Session.SeatReserveCommand(
                        seatId = 1L,
                        discountPolicy,
                    ),
                    Session.SeatReserveCommand(
                        seatId = 2L,
                        discountPolicy,
                    ),
                ),
                discountFactor = DiscountFactor(
                    reserveDateTime = LocalDateTime.of(2025, 1, 1, 0, 0),
                    ticketTotalAmount = 1,
                ),
            ),
        )

        //then
        assertThat(reservation.getTotalAmount()).isEqualTo(Money.of(14000))
        assertThat(reservation.getTickets().size).isEqualTo(2)
    }

    @Test
    fun `없는 좌석에 예매를 요청하면 예외를 반환한다`() {
        //given
        val performance = Performance(name = "공연공연")
        val session = Session(
            performance = performance,
            seats = listOf(seatFixtures(1L, Money.of(10000)), seatFixtures(2L, Money.of(10000))),
        )
        val discountPolicy = PercentDiscountPolicy(
            percent = 0.3,
            name = "2025년 새해 기념 30% 할인 ",
            conditions = arrayOf(
                DateRangeCondition(
                    startDateTime = LocalDateTime.of(2025, 1, 1, 0, 0),
                    endDateTime = LocalDateTime.of(2025, 1, 10, 0, 0),
                ),
            ),
        )

        // when
        // then
        Assertions.assertThatThrownBy {
            session.reserve(
                Session.ReserveCommand(
                    customer = Customer("김철수"),
                    seatCommands = listOf(
                        Session.SeatReserveCommand(
                            seatId = 3L,
                            discountPolicy,
                        ),
                    ),
                    discountFactor = DiscountFactor(
                        reserveDateTime = LocalDateTime.of(2025, 1, 1, 0, 0),
                        ticketTotalAmount = 1,
                    ),
                ),
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    private fun seatFixtures(id: Long, money: Money): Seat {
        return Seat(
            id = id,
            seatStatus = SeatStatus.UN_RESERVED,
            seatClass = SeatClass(
                price = money,
            ),
        )
    }
}

package com.performance.web.api.reservation.domain

import com.performance.web.api.common.domain.Money
import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.fixtures.*
import com.performance.web.api.mock.FakeSeatRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


class SeatReservationTest {

    private lateinit var seatReservation: SeatReservation

    @BeforeEach
    fun setUp() {
        seatReservation = SeatReservation(FakeSeatRepository())
    }

    @Test()
    fun `예매와 티켓을 함께 생성해 리턴한다 `() {
        //given
        val performance = PerformanceFixture.create(
            name = "공연1",
            runTimeMinutes = 60,
        )
        val session = SessionFixture.create(
            startDateTime = LocalDateTime.of(2025, 2, 1, 12, 0, 0),
        )
        val customer = Customer(id = 1L)
        val discountFactor = DiscountFactorFixture.create(
            reserveDateTime = LocalDateTime.of(2025, 2, 1, 12, 0, 0),
        )
        val commands = listOf(
            SeatReservation.SeatReserveCommand(
                seat = SeatFixture.create(
                    seatClass = SeatClassFixture.create(price = Money.of(10000), classType = "VIP"),
                ),
                discountPolicy = DiscountPolicy.none(),
            ),
            SeatReservation.SeatReserveCommand(
                seat = SeatFixture.create(
                    seatClass = SeatClassFixture.create(price = Money.of(10000), classType = "VIP"),
                ),
                discountPolicy = DiscountPolicyFixture.createPercent(percent = 0.5, name = "할인할인"),
            ),
        )

        // when
        val reservation = seatReservation.reserve(performance, customer, session, commands, discountFactor)

        //then
        assertThat(reservation.getTotalAmount()).isEqualTo(Money.of(15000))
        assertThat(reservation.getCustomer().getId()).isEqualTo(1L)
        assertThat(reservation.getPerformanceSessionInfo().performanceName).isEqualTo("공연1")
        assertThat(reservation.getPerformanceSessionInfo().sessionStartDate).isEqualTo(LocalDate.of(2025, 2, 1))
        assertThat(reservation.getPerformanceSessionInfo().sessionStartTime).isEqualTo(LocalTime.of(12, 0, 0))
        assertThat(reservation.getPerformanceSessionInfo().sessionEndTime).isEqualTo(LocalTime.of(13, 0, 0))
    }
}

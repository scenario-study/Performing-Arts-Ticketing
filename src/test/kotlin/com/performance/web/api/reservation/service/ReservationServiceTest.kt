package com.performance.web.api.reservation.service

import com.performance.web.api.common.domain.Money
import com.performance.web.api.discount.domain.DiscountPolicySelector
import com.performance.web.api.discount.domain.PercentDiscountPolicy
import com.performance.web.api.mock.*
import com.performance.web.api.reservation.domain.SeatReservation
import com.performance.web.api.reservation.service.dto.ReservationCommand
import com.performance.web.api.seat.domain.Seat
import com.performance.web.api.seat.domain.SeatClass
import com.performance.web.api.seat.domain.SeatPosition
import com.performance.web.api.seat.domain.SeatStatus
import com.performance.web.api.session.domain.Session
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ReservationServiceTest {

    private lateinit var reservationService: ReservationService

    @BeforeEach
    fun initReservationService() {
        val sessionRepository = FakeSessionRepository()
        val reservationRepository = FakeReservationRepository()
        val seatRepository = FakeSeatRepository()
        val memberRepository = FakeMemberRepository()
        val performanceRepository = FakePerformanceRepository()
        val discountPolicyRepository = FakeDiscountPolicyRepository();
        val discountPolicySelector = DiscountPolicySelector(discountPolicyRepository)
        val seatReservation = SeatReservation(seatRepository)

//        PercentDiscountPolicy(
//            id = 2L,
//            percent = 0.3,
//            name = "테스트 할인",
//            seatClassId = 1L,
//        )
//        PercentDiscountPolicy(
//            id = 1L,
//            percent = 0.3,
//            name = "테스트 할인",
//        ),
        val seatClass1 = SeatClass(
            price = Money.of(10000),
            classType = "VIP",
        )
        val seatClass2 = SeatClass(
            price = Money.of(10000),
            classType = "VIP",

        )
        val seats = listOf(
            Seat(
                id = 1L,
                seatClass = seatClass1,
                seatStatus = SeatStatus.UN_RESERVED,
                seatPosition = SeatPosition(1, 1, 1),
            ),
            Seat(
                id = 2L,
                seatClass = seatClass2,
                seatStatus = SeatStatus.UN_RESERVED,
                seatPosition = SeatPosition(1, 1, 1),
            ),
        )

        val session = Session(
            performanceId = 1L,
            startDateTime = LocalDateTime.of(2025, 1, 10, 12, 30),
        )

        sessionRepository.save(session)

        reservationService = ReservationService(
            sessionRepository = sessionRepository,
            reservationRepository = reservationRepository,
            seatRepository =  seatRepository,
            memberRepository = memberRepository,
            discountPolicySelector = discountPolicySelector,
            seatReservation =  seatReservation,
            performanceRepository = performanceRepository
        )
    }

    @Test
    fun `reserve 을 통해 예매를 생성할 수 있다`() {
        // given
        val command = ReservationCommand(
            customerId = 1L,
            sessionId = 1L,
            seatCommands = listOf(
                ReservationCommand.ReservationSeatCommand(
                    seatId = 1L,
                    discountPolicyId = 1L,
                ),
                ReservationCommand.ReservationSeatCommand(
                    seatId = 2L,
                    discountPolicyId = 2L,
                ),
            ),
        )


        // when
        val result = reservationService.reserve(command)


        // then
        assertThat(result.getId()).isEqualTo(1L)
        assertThat(result.getTotalAmount()).isEqualTo(Money.of(14000))
    }


    @Test
    fun `reserve 시 할인 입력을 안하면 기본 금액으로 예매를 생성한다`() {
        val command = ReservationCommand(
            customerId = 1L,
            sessionId = 1L,
            seatCommands = listOf(
                ReservationCommand.ReservationSeatCommand(
                    seatId = 1L,
                    discountPolicyId = null,
                ),
                ReservationCommand.ReservationSeatCommand(
                    seatId = 2L,
                    discountPolicyId = null,
                ),
            ),
        )


        // when
        val result = reservationService.reserve(command)


        // then
        assertThat(result.getId()).isEqualTo(1L)
        assertThat(result.getTotalAmount()).isEqualTo(Money.of(20000))
    }


    @Test
    fun `findById 시 없는 id로 요청하면 예외를 반환한다`() {
        //given
        val id = 1L

        //when
        //then
        assertThatThrownBy {
            reservationService.findById(id);
        }.hasMessageContaining("${id}의 Reservation을 찾을 수 없습니다.")
    }
}

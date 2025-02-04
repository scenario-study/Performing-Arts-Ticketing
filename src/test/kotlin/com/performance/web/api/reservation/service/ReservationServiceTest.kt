package com.performance.web.api.reservation.service

import com.performance.web.api.common.domain.Money
import com.performance.web.api.common.domain.ResourceNotFoundException
import com.performance.web.api.discount.domain.DiscountPolicySelector
import com.performance.web.api.discount.domain.PercentDiscountPolicy
import com.performance.web.api.member.domain.Member
import com.performance.web.api.mock.*
import com.performance.web.api.performance.domain.Performance
import com.performance.web.api.performance.domain.PerformanceSeatClass
import com.performance.web.api.reservation.domain.*
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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationServiceTest {

    private lateinit var reservationService: ReservationService
    private lateinit var reservationRepository: ReservationRepository
    @BeforeEach
    fun initReservationService() {
        val memberRepository = FakeMemberRepository()
        val sessionRepository = FakeSessionRepository()
        val seatRepository = FakeSeatRepository()
        val performanceRepository = FakePerformanceRepository()
        val discountPolicyRepository = FakeDiscountPolicyRepository();
        reservationRepository = FakeReservationRepository()
        val discountPolicySelector = DiscountPolicySelector(discountPolicyRepository)
        val seatReservation = SeatReservation(seatRepository)

        memberRepository.save(Member(name = "김철수"))
        sessionRepository.save(Session(
            performanceId = 1L,
            startDateTime = LocalDateTime.of(2025, 1, 10, 12, 30),
        ))
        seatRepository.save(
            Seat(
                seatClass = SeatClass(price = Money.of(10000), classType = "VIP"),
                seatPosition = SeatPosition(1,1,1),
                seatStatus = SeatStatus.UN_RESERVED,
                sessionId = 1L
            )
        )
        seatRepository.save(
            Seat(
                seatClass = SeatClass(price = Money.of(10000), classType = "VIP"),
                seatPosition = SeatPosition(1,2,1),
                seatStatus = SeatStatus.UN_RESERVED,
                sessionId = 1L
            )
        )
        discountPolicyRepository.save(
            PercentDiscountPolicy(
                name = "50%할인",
                percent = 0.5,
                seatClassId = 1L,
            )
        )
        performanceRepository.save(
            Performance(
                name = "공연1",
                runTimeInMinutes = 180,
                startDate = LocalDate.of(2025,2,1),
                endDate = LocalDate.of(2025,2,20),
                description = "공연입니다",
                seatClasses = listOf(
                    PerformanceSeatClass(price = Money.of(10000), classType = "VIP"),
                )
            )
        )



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
                    discountPolicyId = 1L,
                ),
            ),
        )


        // when
        val result = reservationService.reserve(command)

        // then
        assertThat(result.getId()).isEqualTo(1L)
        assertThat(result.getTickets().size).isEqualTo(2)
        assertThat(result.getTotalAmount()).isEqualTo(Money.of(10000))
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
        val id = 2L

        //when
        //then
        assertThatThrownBy {
            reservationService.findById(id);
        }.isInstanceOf(ResourceNotFoundException::class.java)
    }


    @Test
    fun `findById시 조회가 성공하면 정상적으로 예매를 반환한다`(){
        // givne
        reservationRepository.save(
            Reservation(
                sessionId = 1L,
                performanceSessionInfo = PerformanceSessionInfo(
                    performanceName = "공연",
                    sessionStartDate = LocalDate.now(),
                    sessionStartTime = LocalTime.now(),
                    sessionEndTime = LocalTime.now(),
                ),
                customer = Customer(1L),
                tickets = listOf(Ticket(
                    totalAmount = Money.of(10000),
                    regularPrice = Money.of(10000),
                    ticketSeatInfo = TicketSeatInfo(1,1,1,"VIP"),
                    discountInfo = DiscountInfo("할인 적용 X"),
                ))
            )
        )

        //when
        val result = reservationService.findById(1L)

        assertThat(result.getId()).isEqualTo(1L)
        assertThat(result.getTotalAmount()).isEqualTo(Money.of(10000))
        assertThat(result.getCustomer().getId()).isEqualTo(1L)
        assertThat(result.getTickets().size).isEqualTo(1)
    }
}

package com.hunhui.ticketworld.domain.performance

import com.hunhui.ticketworld.common.error.AssertUtil.assertErrorCode
import com.hunhui.ticketworld.domain.performance.exception.PerformanceErrorCode
import com.hunhui.ticketworld.domain.performance.exception.PerformanceErrorCode.TICKET_GRADE_IS_EMPTY
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class PerformanceTest {
    @Test
    fun `모든 필드가 유효할 때 Performance 생성에 성공한다`() {
        // when
        val performance = PerformanceFixtureFactory.createValidPerformance()

        // then
        assertNotNull(performance.id)
        assertTrue(performance.ticketGrades.isNotEmpty())
        assertTrue(performance.rounds.isNotEmpty())
    }

    @Test
    fun `좌석 등급이 비어있으면 예외가 발생한다`() {
        // when & then
        assertErrorCode(TICKET_GRADE_IS_EMPTY) {
            PerformanceFixtureFactory.createValidPerformance(ticketGrades = emptyList())
        }
    }

    @Test
    fun `공연 회차가 비어있으면 예외가 발생한다`() {
        // when & then
        assertErrorCode(PerformanceErrorCode.ROUND_IS_EMPTY) {
            PerformanceFixtureFactory.createValidPerformance(rounds = emptyList())
        }
    }

    @Test
    fun `startDate는 공연 회차 중 가장 빠른 날짜를 반환한다`() {
        // given
        val performanceRound1 =
            PerformanceRound.create(
                LocalDateTime.of(2025, 1, 10, 18, 0),
                LocalDateTime.of(2025, 1, 1, 0, 0),
                LocalDateTime.of(2025, 1, 5, 0, 0),
            )
        val performanceRound2 =
            PerformanceRound.create(
                LocalDateTime.of(2025, 1, 8, 18, 0),
                LocalDateTime.of(2025, 1, 1, 0, 0),
                LocalDateTime.of(2025, 1, 5, 0, 0),
            )
        val performance =
            PerformanceFixtureFactory.createValidPerformance(
                rounds = listOf(performanceRound1, performanceRound2),
            )

        // when
        val startDate = performance.startDate

        // then
        assertEquals(LocalDate.of(2025, 1, 8), startDate)
    }

    @Test
    fun `finishDate는 공연 회차 중 가장 늦은 날짜를 반환한다`() {
        // given
        val performanceRound1 =
            PerformanceRound.create(
                LocalDateTime.of(2025, 1, 7, 18, 0),
                LocalDateTime.of(2025, 1, 1, 0, 0),
                LocalDateTime.of(2025, 1, 5, 0, 0),
            )
        val performanceRound2 =
            PerformanceRound.create(
                LocalDateTime.of(2025, 1, 10, 18, 0),
                LocalDateTime.of(2025, 1, 2, 0, 0),
                LocalDateTime.of(2025, 1, 5, 0, 0),
            )
        val performance =
            PerformanceFixtureFactory.createValidPerformance(
                rounds = listOf(performanceRound1, performanceRound2),
            )

        // when
        val finishDate = performance.finishDate

        // then
        assertEquals(LocalDate.of(2025, 1, 10), finishDate)
    }

    @Test
    fun `availableRounds는 예약 가능한 회차만 필터링한다`() {
        // given
        val now = LocalDateTime.now()
        val availableRound =
            PerformanceRound.create(
                now.plusDays(2),
                now.minusDays(1),
                now.plusDays(1),
            )
        val unavailableRound =
            PerformanceRound.create(
                now.plusDays(2),
                now.minusDays(3),
                // 이미 끝남
                now.minusDays(2),
            )
        val performance =
            PerformanceFixtureFactory.createValidPerformance(rounds = listOf(availableRound, unavailableRound))

        // when
        val availableRounds = performance.availableRounds

        // then
        assertEquals(1, availableRounds.size)
        assertTrue(availableRounds.contains(availableRound))
        assertFalse(availableRounds.contains(unavailableRound))
    }

    @Test
    fun `minimumReservationStartDateTime는 rounds 중 가장 이전인 예약 시작 시간을 반환한다`() {
        // given
        val round1 =
            PerformanceRound.create(
                performanceDateTime = LocalDateTime.of(2025, 1, 10, 18, 0),
                reservationStartDateTime = LocalDateTime.of(2025, 1, 1, 0, 0),
                reservationFinishDateTime = LocalDateTime.of(2025, 1, 5, 0, 0),
            )
        val round2 =
            PerformanceRound.create(
                performanceDateTime = LocalDateTime.of(2025, 1, 15, 18, 0),
                reservationStartDateTime = LocalDateTime.of(2025, 1, 2, 0, 0),
                reservationFinishDateTime = LocalDateTime.of(2025, 1, 6, 0, 0),
            )
        val round3 =
            PerformanceRound.create(
                performanceDateTime = LocalDateTime.of(2025, 1, 20, 18, 0),
                reservationStartDateTime = LocalDateTime.of(2025, 1, 3, 0, 0),
                reservationFinishDateTime = LocalDateTime.of(2025, 1, 7, 0, 0),
            )

        val performance =
            PerformanceFixtureFactory.createValidPerformance(
                rounds = listOf(round1, round2, round3),
            )

        // when
        val earliestReservationStart = performance.minimumReservationStartDateTime

        // then
        assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0), earliestReservationStart)
    }

    @Test
    fun `minimumPrice는 ticketGrades 중 가장 낮은 금액을 반환한다`() {
        // given
        val ticketGrade1 = TicketGrade.create("VIP", 50000)
        val ticketGrade2 = TicketGrade.create("R석", 40000)
        val performance =
            PerformanceFixtureFactory.createValidPerformance(ticketGrades = listOf(ticketGrade1, ticketGrade2))

        // when
        val minPrice = performance.minimumPrice

        // then
        assertEquals(40000L, minPrice.amount)
    }
}

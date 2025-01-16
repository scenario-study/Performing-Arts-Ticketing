package com.hunhui.ticketworld.domain.seat

import com.hunhui.ticketworld.domain.seat.exception.InvalidSeatException
import com.hunhui.ticketworld.domain.seat.exception.SeatErrorCode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SeatTest {
    @Test
    fun `유효한 좌표로 Seat 생성 성공`() {
        // given
        val seat = SeatFixtureFactory.createValidSeat()

        // then
        assertNotNull(seat)
        assertEquals("A1", seat.seatName)
    }

    @Test
    fun `x나 y가 음수면 InvalidSeatException 발생`() {
        // when & then
        val exception1 =
            assertThrows<InvalidSeatException> {
                SeatFixtureFactory.createValidSeat(x = -1, y = 5)
            }
        val exception2 =
            assertThrows<InvalidSeatException> {
                SeatFixtureFactory.createValidSeat(x = 5, y = -1)
            }

        assertEquals(SeatErrorCode.POSITION_NEGATIVE, exception1.errorCode)
        assertEquals(SeatErrorCode.POSITION_NEGATIVE, exception2.errorCode)
    }
}

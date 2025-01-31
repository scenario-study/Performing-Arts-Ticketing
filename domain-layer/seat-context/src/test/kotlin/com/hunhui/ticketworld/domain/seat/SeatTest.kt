package com.hunhui.ticketworld.domain.seat

import com.hunhui.ticketworld.common.error.AssertUtil.assertErrorCode
import com.hunhui.ticketworld.domain.seat.exception.SeatErrorCode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

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
        assertErrorCode(SeatErrorCode.POSITION_IS_NEGATIVE) {
            SeatFixtureFactory.createValidSeat(x = -1, y = 5)
        }
        assertErrorCode(SeatErrorCode.POSITION_IS_NEGATIVE) {
            SeatFixtureFactory.createValidSeat(x = 0, y = -1)
        }
    }
}

package com.hunhui.ticketworld.domain.seat

import com.hunhui.ticketworld.common.error.AssertUtil.assertErrorCode
import com.hunhui.ticketworld.domain.seat.exception.SeatErrorCode.SEAT_IS_EMPTY
import com.hunhui.ticketworld.domain.seat.exception.SeatErrorCode.SEAT_NOT_CONTAINED
import com.hunhui.ticketworld.domain.seat.exception.SeatErrorCode.WIDTH_HEIGHT_IS_NOT_POSITIVE
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class SeatAreaTest {
    @Test
    fun `유효한 SeatArea 생성 성공`() {
        // given
        val seats =
            listOf(
                SeatFixtureFactory.createValidSeat(x = 2, y = 3),
                SeatFixtureFactory.createValidSeat(x = 9, y = 5),
            )
        val seatArea = SeatFixtureFactory.createValidSeatArea(width = 10, height = 10, seats = seats)

        // then
        assertNotNull(seatArea)
        assertEquals(2, seatArea.seats.size)
    }

    @Test
    fun `너비나 높이가 0 이하면 InvalidSeatAreaException 발생`() {
        // when & then
        assertErrorCode(WIDTH_HEIGHT_IS_NOT_POSITIVE) {
            SeatFixtureFactory.createValidSeatArea(width = 0, height = 5)
        }
        assertErrorCode(WIDTH_HEIGHT_IS_NOT_POSITIVE) {
            SeatFixtureFactory.createValidSeatArea(width = 5, height = -1)
        }
    }

    @Test
    fun `좌석이 없으면 InvalidSeatAreaException 발생`() {
        // when & then
        assertErrorCode(SEAT_IS_EMPTY) {
            SeatFixtureFactory.createValidSeatArea(seats = emptyList())
        }
    }

    @Test
    fun `좌석이 영역의 범위를 벗어나면 InvalidSeatAreaException 발생`() {
        // given
        val seats1 =
            listOf(
                SeatFixtureFactory.createValidSeat(x = 2, y = 3),
                SeatFixtureFactory.createValidSeat(x = 10, y = 9),
            )
        val seats2 =
            listOf(
                SeatFixtureFactory.createValidSeat(x = 2, y = 3),
                SeatFixtureFactory.createValidSeat(x = 9, y = 10),
            )

        // when & then
        assertErrorCode(SEAT_NOT_CONTAINED) {
            SeatFixtureFactory.createValidSeatArea(width = 10, height = 10, seats = seats1)
        }
        assertErrorCode(SEAT_NOT_CONTAINED) {
            SeatFixtureFactory.createValidSeatArea(width = 10, height = 10, seats = seats2)
        }
    }
}

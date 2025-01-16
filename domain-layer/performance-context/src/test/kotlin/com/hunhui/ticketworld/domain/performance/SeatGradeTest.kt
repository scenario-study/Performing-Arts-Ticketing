package com.hunhui.ticketworld.domain.performance

import io.mockk.every
import io.mockk.mockkStatic
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.UUID

class SeatGradeTest {
    @Test
    fun `유효한 등급 이름과 가격으로 SeatGrade를 생성할 수 있다`() {
        // uuid Mocking
        val uuid = UUID.randomUUID()
        mockkStatic(UUID::class)
        every { UUID.randomUUID() } returns uuid

        // given
        val seatGrade = SeatGrade.create("VIP", 20000)

        // then
        assertEquals(uuid, seatGrade.id)
        assertEquals("VIP", seatGrade.gradeName)
        assertEquals(20000, seatGrade.price.amount)
    }
}

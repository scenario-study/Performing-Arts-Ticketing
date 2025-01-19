package com.hunhui.ticketworld.domain.performance

import io.mockk.every
import io.mockk.mockkStatic
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.UUID

class PerformancePriceTest {
    @Test
    fun `유효한 속성들로 PerformancePrice를 생성할 수 있다`() {
        // uuid Mocking
        val uuid = UUID.randomUUID()
        mockkStatic(UUID::class)
        every { UUID.randomUUID() } returns uuid

        // given
        val performancePrice = PerformancePrice.create("VIP", 20000)

        // then
        assertEquals(uuid, performancePrice.id)
        assertEquals("VIP", performancePrice.priceName)
        assertEquals(20000, performancePrice.price.amount)
    }
}

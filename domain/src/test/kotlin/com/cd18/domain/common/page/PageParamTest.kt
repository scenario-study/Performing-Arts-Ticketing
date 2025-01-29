package com.cd18.domain.common.page

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import kotlin.test.Test

class PageParamTest {
    @Test
    fun `should create PageParam with valid values`() {
        val pageParam = PageParam(page = 1, size = 10)

        assertEquals(1, pageParam.page)
        assertEquals(10, pageParam.size)
    }

    @Test
    fun `should throw IllegalArgumentException when page is less than 0`() {
        val exception =
            assertThrows(IllegalArgumentException::class.java) {
                PageParam(page = -1, size = 10)
            }

        assertEquals("Page must be greater than or equal to 0", exception.message)
    }

    @Test
    fun `should throw IllegalArgumentException when size is less than 1`() {
        val exception =
            assertThrows(IllegalArgumentException::class.java) {
                PageParam(page = 1, size = 0)
            }

        assertEquals("Size must be between 1 and 20", exception.message)
    }

    @Test
    fun `should throw IllegalArgumentException when size is greater than 20`() {
        val exception =
            assertThrows(IllegalArgumentException::class.java) {
                PageParam(page = 1, size = 21)
            }

        assertEquals("Size must be between 1 and 20", exception.message)
    }

    @Test
    fun `should create PageParam with default values`() {
        val pageParam = PageParam()

        assertEquals(0, pageParam.page)
        assertEquals(10, pageParam.size)
    }
}

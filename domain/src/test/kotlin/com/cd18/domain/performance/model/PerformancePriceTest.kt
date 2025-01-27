package com.cd18.domain.performance.model

import com.cd18.common.exception.BaseException
import com.cd18.domain.performance.enums.PerformanceInfoErrorCode
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PerformancePriceTest {
    @Test
    fun `should create PerformancePrice with valid values`() {
        val performancePrice =
            PerformancePrice(
                performanceId = 1,
                performancePriceId = 100,
                performanceOriginPrice = 1000,
                performanceDiscountId = 10,
                performanceDiscountPrice = 200,
            )

        assertEquals(1000, performancePrice.performanceOriginPrice)
        assertEquals(200, performancePrice.performanceDiscountPrice)
        assertEquals(20, performancePrice.performanceDiscountRate) // 200 / 1000 * 100
    }

    @Test
    fun `should change discount price successfully`() {
        val performancePrice =
            PerformancePrice(
                performanceId = 1,
                performancePriceId = 100,
                performanceOriginPrice = 1000,
                performanceDiscountId = 10,
                performanceDiscountPrice = 200,
            )

        val updatedPerformancePrice = performancePrice.changeDiscountPrice(300)

        assertEquals(300, updatedPerformancePrice.performanceDiscountPrice)
    }

    @Test
    fun `should throw exception if discount price is negative`() {
        val performancePrice =
            PerformancePrice(
                performanceId = 1,
                performancePriceId = 100,
                performanceOriginPrice = 1000,
                performanceDiscountId = 10,
                performanceDiscountPrice = 200,
            )

        val exception =
            assertFailsWith<BaseException> {
                performancePrice.changeDiscountPrice(-100)
            }

        assertEquals(PerformanceInfoErrorCode.INVALID_DISCOUNT_PRICE_NOT_POSITIVE, exception.errorCode)
    }

    @Test
    fun `should throw exception if discount price is greater than origin price`() {
        val performancePrice =
            PerformancePrice(
                performanceId = 1,
                performancePriceId = 100,
                performanceOriginPrice = 1000,
                performanceDiscountId = 10,
                performanceDiscountPrice = 200,
            )

        val exception =
            assertFailsWith<BaseException> {
                performancePrice.changeDiscountPrice(1200)
            }

        assertEquals(PerformanceInfoErrorCode.INVALID_DISCOUNT_PRICE_OVER_ORIGIN_PRICE, exception.errorCode)
    }

    @Test
    fun `should throw exception if discount price is same as current price`() {
        val performancePrice =
            PerformancePrice(
                performanceId = 1,
                performancePriceId = 100,
                performanceOriginPrice = 1000,
                performanceDiscountId = 10,
                performanceDiscountPrice = 200,
            )

        val exception =
            assertFailsWith<BaseException> {
                performancePrice.changeDiscountPrice(200)
            }

        assertEquals(PerformanceInfoErrorCode.INVALID_DISCOUNT_PRICE_SAME, exception.errorCode)
    }
}

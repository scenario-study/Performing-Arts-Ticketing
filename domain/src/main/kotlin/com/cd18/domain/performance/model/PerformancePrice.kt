package com.cd18.domain.performance.model

import com.cd18.common.exception.BaseException
import com.cd18.common.util.isNotNegative
import com.cd18.domain.performance.enums.PerformanceInfoErrorCode
import kotlin.math.roundToInt

open class PerformancePrice(
    val performanceId: Long = 0,
    val performancePriceId: Long = 0,
    open val performanceOriginPrice: Int,
    val performanceDiscountId: Long = 0,
    open val performanceDiscountPrice: Int,
) {
    val performanceDiscountRate: Int
        get() = calculateDiscountRate()

    fun changeDiscountPrice(discountPrice: Int): PerformancePrice {
        validateDiscountPrice(discountPrice)

        return PerformancePrice(
            performanceId = performanceId,
            performancePriceId = performancePriceId,
            performanceOriginPrice = performanceOriginPrice,
            performanceDiscountId = performanceDiscountId,
            performanceDiscountPrice = discountPrice,
        )
    }

    private fun calculateDiscountRate(): Int = ((performanceDiscountPrice.toDouble() / performanceOriginPrice) * 100).roundToInt()

    private fun validateDiscountPrice(discountPrice: Int) {
        if (!discountPrice.isNotNegative()) {
            throw BaseException(PerformanceInfoErrorCode.INVALID_DISCOUNT_PRICE_NOT_POSITIVE)
        }

        if (discountPrice > performanceOriginPrice) {
            throw BaseException(PerformanceInfoErrorCode.INVALID_DISCOUNT_PRICE_OVER_ORIGIN_PRICE)
        }

        if (discountPrice == performanceDiscountPrice) {
            throw BaseException(PerformanceInfoErrorCode.INVALID_DISCOUNT_PRICE_SAME)
        }
    }
}

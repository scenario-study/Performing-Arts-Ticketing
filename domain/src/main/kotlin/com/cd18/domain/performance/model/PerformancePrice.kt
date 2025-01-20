package com.cd18.domain.performance.model

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

    private fun calculateDiscountRate(): Int = ((performanceDiscountPrice.toDouble() / performanceOriginPrice) * 100).roundToInt()
}

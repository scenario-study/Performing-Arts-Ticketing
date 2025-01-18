package com.cd18.domain.performance.model

import kotlin.math.roundToInt

open class PerformancePrice(
    open val performanceOriginPrice: Int,
    open val performanceDiscountPrice: Int,
) {
    val performanceDiscountRate: Int
        get() = calculateDiscountRate()

    private fun calculateDiscountRate(): Int = ((performanceDiscountPrice.toDouble() / performanceOriginPrice) * 100).roundToInt()
}

package com.cd18.domain.performance.dto

import com.cd18.domain.performance.model.PerformancePrice

data class PerformancePriceDto(
    val performanceId: Long,
    val priceId: Long,
    val originPrice: Int,
    val discountId: Long,
    val discountPrice: Int,
) {
    fun toPerformancePrice(): PerformancePrice {
        return PerformancePrice(
            performanceId = performanceId,
            performancePriceId = priceId,
            performanceOriginPrice = originPrice,
            performanceDiscountId = discountId,
            performanceDiscountPrice = discountPrice,
        )
    }
}

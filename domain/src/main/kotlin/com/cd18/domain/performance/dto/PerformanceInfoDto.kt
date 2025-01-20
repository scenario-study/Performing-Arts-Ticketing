package com.cd18.domain.performance.dto

import com.cd18.domain.performance.model.PerformancePrice

data class PerformanceInfoDto(
    val id: Long,
    val performanceName: String,
    val performanceVenue: String,
    override val performanceOriginPrice: Int,
    override val performanceDiscountPrice: Int,
    val startDate: String,
    val endDate: String,
) : PerformancePrice(
        performanceOriginPrice = performanceOriginPrice,
        performanceDiscountPrice = performanceDiscountPrice,
    )

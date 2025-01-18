package com.cd18.domain.performance.dto

import com.cd18.domain.performance.model.PerformancePrice

data class PerformanceInfoDetailDto(
    val id: Long,
    val performanceName: String,
    val performanceDescription: String,
    val performanceVenue: String,
    override val performanceOriginPrice: Int,
    override val performanceDiscountPrice: Int,
    val startDate: String,
    val endDate: String,
) : PerformancePrice(performanceOriginPrice, performanceDiscountPrice)

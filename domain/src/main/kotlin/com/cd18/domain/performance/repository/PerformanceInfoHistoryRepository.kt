package com.cd18.domain.performance.repository

interface PerformanceInfoHistoryRepository {
    fun saveDiscountChangeHistory(
        performanceId: Long,
        performanceDiscountId: Long,
        discountPrice: Int,
    )
}

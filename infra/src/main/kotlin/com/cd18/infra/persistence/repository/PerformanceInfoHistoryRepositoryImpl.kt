package com.cd18.infra.persistence.repository

import com.cd18.domain.performance.repository.PerformanceInfoHistoryRepository
import com.cd18.infra.persistence.model.HistoryPerformanceDiscount
import com.cd18.infra.persistence.repository.jpa.HistoryPerformanceDiscountJpaRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class PerformanceInfoHistoryRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
    private val historyPerformanceDiscountJpaRepository: HistoryPerformanceDiscountJpaRepository,
) : PerformanceInfoHistoryRepository {
    override fun saveDiscountChangeHistory(
        performanceId: Long,
        performanceDiscountId: Long,
        discountPrice: Int,
    ) {
        historyPerformanceDiscountJpaRepository.save(
            HistoryPerformanceDiscount(
                performanceInfoId = performanceId,
                performanceDiscountId = performanceDiscountId,
                discountPrice = discountPrice,
            ),
        )
    }
}

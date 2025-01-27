package com.cd18.infra.persistence.repository

import com.cd18.domain.metrics.dto.TargetCountSummaryDto
import com.cd18.domain.performance.repository.PerformanceInfoStatsRepository
import com.cd18.infra.persistence.model.PerformanceHourlyViewStat
import com.cd18.infra.persistence.repository.jpa.PerformanceHourlyViewStatJpaRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class PerformanceInfoStatsRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
    private val performanceHourlyViewStatJpaRepository: PerformanceHourlyViewStatJpaRepository,
) : PerformanceInfoStatsRepository {
    override fun savePerformanceHourlyViewStats(
        date: LocalDate,
        hour: Int,
        countSummary: List<TargetCountSummaryDto>,
    ) {
        countSummary.forEach {
            performanceHourlyViewStatJpaRepository.save(
                PerformanceHourlyViewStat(
                    statisticDate = date,
                    statisticHour = hour,
                    performanceInfoId = it.targetId,
                    viewCount = it.count,
                ),
            )
        }
    }
}

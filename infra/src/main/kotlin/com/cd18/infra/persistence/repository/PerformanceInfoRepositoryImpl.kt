package com.cd18.infra.persistence.repository

import com.cd18.common.exception.BaseException
import com.cd18.domain.common.page.PageParam
import com.cd18.domain.performance.dto.PerformanceInfoDetailDto
import com.cd18.domain.performance.dto.PerformanceInfoDto
import com.cd18.domain.performance.enums.PerformanceInfoErrorCode
import com.cd18.domain.performance.repository.PerformanceInfoRepository
import com.cd18.infra.persistence.config.applyPagination
import com.cd18.infra.persistence.model.QPerformanceDiscount.performanceDiscount
import com.cd18.infra.persistence.model.QPerformanceInfo.performanceInfo
import com.cd18.infra.persistence.model.QPerformancePrice.performancePrice
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class PerformanceInfoRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : PerformanceInfoRepository {
    override fun getList(pageParam: PageParam): List<PerformanceInfoDto> {
        return queryFactory.select(
            Projections.constructor(
                PerformanceInfoDto::class.java,
                performanceInfo.id,
                performanceInfo.name,
                performanceInfo.venue,
                performancePrice.price,
                performanceDiscount.discountPrice,
                performanceInfo.startDate,
                performanceInfo.endDate,
            ),
        ).from(performanceInfo)
            .leftJoin(performancePrice)
            .on(performanceInfo.id.eq(performancePrice.performanceInfoId))
            .leftJoin(performanceDiscount)
            .on(performanceInfo.id.eq(performanceDiscount.performanceInfoId))
            .orderBy(performanceInfo.id.desc())
            .applyPagination(pageParam)
            .fetch()
    }

    override fun getById(id: Long): PerformanceInfoDetailDto {
        return queryFactory.select(
            Projections.constructor(
                PerformanceInfoDetailDto::class.java,
                performanceInfo.id,
                performanceInfo.name,
                performanceInfo.description,
                performanceInfo.venue,
                performancePrice.price,
                performanceDiscount.discountPrice,
                performanceInfo.startDate,
                performanceInfo.endDate,
            ),
        ).from(performanceInfo)
            .leftJoin(performancePrice)
            .on(performanceInfo.id.eq(performancePrice.performanceInfoId))
            .leftJoin(performanceDiscount)
            .on(performanceInfo.id.eq(performanceDiscount.performanceInfoId))
            .where(performanceInfo.id.eq(id))
            .fetchFirst()
            ?: throw BaseException(PerformanceInfoErrorCode.NOT_FOUND)
    }
}

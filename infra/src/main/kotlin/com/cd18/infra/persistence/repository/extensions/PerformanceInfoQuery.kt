package com.cd18.infra.persistence.repository.extensions

import com.cd18.infra.persistence.model.QPerformanceDiscount.performanceDiscount
import com.cd18.infra.persistence.model.QPerformanceInfo.performanceInfo
import com.cd18.infra.persistence.model.QPerformancePrice.performancePrice
import com.querydsl.jpa.impl.JPAQuery

fun <T> JPAQuery<T>.leftJoinPerformancePrice(): JPAQuery<T> {
    return this.leftJoin(performancePrice)
        .on(performanceInfo.id.eq(performancePrice.performanceInfoId))
}

fun <T> JPAQuery<T>.leftJoinPerformanceDiscount(): JPAQuery<T> {
    return this.leftJoin(performanceDiscount)
        .on(performanceInfo.id.eq(performanceDiscount.performanceInfoId))
}

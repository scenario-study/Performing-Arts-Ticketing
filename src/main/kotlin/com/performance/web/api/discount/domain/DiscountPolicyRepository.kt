package com.performance.web.api.discount.domain

import com.performance.web.api.common.domain.BaseRepository

interface DiscountPolicyRepository : BaseRepository<DiscountPolicy> {

    fun findAllByPerformanceSeatClassIds(ids: List<Long>): List<DiscountPolicy>
}

package com.performance.web.api.mock

import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.discount.domain.DiscountPolicyRepository
import java.util.*

class FakeDiscountPolicyRepository: DiscountPolicyRepository {

    override fun findById(id: Long): Optional<DiscountPolicy> {
        TODO("Not yet implemented")
    }

    override fun findAllByPerformanceSeatClassIds(ids: List<Long>): List<DiscountPolicy> {
        TODO("Not yet implemented")
    }
}

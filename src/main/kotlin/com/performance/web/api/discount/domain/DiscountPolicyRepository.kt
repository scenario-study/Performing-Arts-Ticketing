package com.performance.web.api.discount.domain

import java.util.Optional

interface DiscountPolicyRepository {

    fun findById(id: Long): Optional<DiscountPolicy>
}

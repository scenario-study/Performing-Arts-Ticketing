package com.performance.web.api.discount.service

import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.discount.domain.DiscountPolicyRepository
import org.springframework.stereotype.Service

@Service
class DiscountService(
    private val discountPolicyRepository: DiscountPolicyRepository,
) {

    fun findById(id: Long): DiscountPolicy =
        discountPolicyRepository.findByIdThrown(id)

}

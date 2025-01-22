package com.performance.web.api.discount.service

import com.performance.web.api.common.domain.ResourceNotFoundException
import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.discount.domain.DiscountPolicyRepository
import com.performance.web.api.discount.domain.NoneDiscountPolicy
import org.springframework.stereotype.Service

@Service
class DiscountService(
    private val discountPolicyRepository: DiscountPolicyRepository,
) {

    fun findById(id: Long): DiscountPolicy =
        discountPolicyRepository.findById(id)
            .orElseThrow { throw ResourceNotFoundException("Discount policy with id $id does not exist") }

    fun findAllByIdsOrDefault(ids: List<Long?>): List<DiscountPolicy> =
        ids.map { id ->
            findByIdOrDefault(id)
        }

    private fun findByIdOrDefault(id: Long?): DiscountPolicy =
        if (id == null) {
            NoneDiscountPolicy()
        } else {
            findById(id)
        }
}

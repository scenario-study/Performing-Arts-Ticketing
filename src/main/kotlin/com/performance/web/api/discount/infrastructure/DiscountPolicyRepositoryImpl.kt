package com.performance.web.api.discount.infrastructure

import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.discount.domain.DiscountPolicyRepository
import com.performance.web.api.discount.infrastructure.jpa.DiscountPolicyJpaRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class DiscountPolicyRepositoryImpl(
    private val discountPolicyJpaRepository: DiscountPolicyJpaRepository
) : DiscountPolicyRepository {
    override fun findById(id: Long): Optional<DiscountPolicy> {
        return discountPolicyJpaRepository.findById(id).map { it.toDomain() }
    }
}

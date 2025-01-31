package com.performance.web.api.discount.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface DiscountPolicyJpaRepository : JpaRepository<DiscountPolicyEntity, Long> {

}

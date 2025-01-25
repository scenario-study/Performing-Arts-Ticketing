package com.performance.web.api.customer.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface CustomerJpaRepository : JpaRepository<CustomerEntity, Long> {
}

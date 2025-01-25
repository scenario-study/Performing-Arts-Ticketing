package com.performance.web.api.customer.infrastructure

import com.performance.web.api.customer.domain.Customer
import com.performance.web.api.customer.domain.CustomerRepository
import com.performance.web.api.customer.infrastructure.jpa.CustomerJpaRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerRepositoryImpl(
    private val customerJpaRepository: CustomerJpaRepository
) : CustomerRepository {

    override fun findById(id: Long): Optional<Customer> {
        return customerJpaRepository.findById(id).map { it.toDomain() }
    }
}

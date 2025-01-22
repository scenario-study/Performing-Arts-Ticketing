package com.performance.web.api.customer.service

import com.performance.web.api.common.domain.ResourceNotFoundException
import com.performance.web.api.customer.domain.Customer
import com.performance.web.api.customer.domain.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {

    fun findById(id: Long): Customer {
        return customerRepository.findById(id)
            .orElseThrow { throw ResourceNotFoundException("Customer with id $id not found") }
    }
}

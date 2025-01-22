package com.performance.web.api.customer.domain

import java.util.Optional


interface CustomerRepository {

    fun findById(id: Long): Optional<Customer>
}

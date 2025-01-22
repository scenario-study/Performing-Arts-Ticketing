package com.performance.web.api.customer.infrastructure

import com.performance.web.api.customer.domain.Customer
import com.performance.web.api.customer.domain.CustomerRepository
import org.springframework.stereotype.Component
import java.util.Collections
import java.util.Optional

@Component
class MemoryBasedCustomerRepositoryImpl : CustomerRepository {

    private final val store =
        Collections.synchronizedMap(
            mutableMapOf<Long, Customer>(
                1L to Customer(1L, "조인혁"),
            ),
        )

    override fun findById(id: Long): Optional<Customer> = Optional.ofNullable(store[id])
}

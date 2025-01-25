package com.performance.web.api.customer.infrastructure.jpa

import com.performance.web.api.customer.domain.Customer
import jakarta.persistence.*

@Entity
@Table(name = "customer")
class CustomerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var name: String

) {


    fun toDomain(): Customer {
        return Customer(
            id = id,
            name = name,
        )
    }


    companion object {
        fun fromDomain(customer: Customer): CustomerEntity {
            return CustomerEntity(
                id = customer.getId(),
                name = customer.getName(),
            )
        }
    }
}

package com.performance.web.api.customer.domain

class Customer(
    id: Long = 0L,
    name: String,
) {

    private val _id = id
    private val _name = name

    fun getId() = _id
    fun getName() = _name
}

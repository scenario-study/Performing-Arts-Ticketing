package com.performance.web.api.reservation.domain

class Customer(
    name: String
) {

    private val _name = name

    fun getName() = _name
}

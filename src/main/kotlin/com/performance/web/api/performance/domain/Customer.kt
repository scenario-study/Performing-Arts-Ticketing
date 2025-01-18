package com.performance.web.api.performance.domain

class Customer(
    name: String
) {

    private val _name = name

    fun getName() = _name
}

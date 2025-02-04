package com.performance.web.api.discount.domain

interface DiscountCondition {

    fun isSatisfiedBy(discountFactor: DiscountFactor): Boolean
}

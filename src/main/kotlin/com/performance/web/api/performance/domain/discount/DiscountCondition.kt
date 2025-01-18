package com.performance.web.api.performance.domain.discount

interface DiscountCondition {

    fun isSatisfiedBy(discountFactor: DiscountFactor): Boolean

}

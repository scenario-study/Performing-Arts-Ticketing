package com.performance.web.api.performance.domain.discount

import com.performance.web.api.common.domain.Money

abstract class DiscountPolicy(
    id: Long,
    name: String,
    vararg conditions: DiscountCondition
) {

    private val _id: Long = id
    private val _name: String = name
    private val _conditions: List<DiscountCondition> = conditions.toList()

    fun calculateFee(price: Money, discountFactor: DiscountFactor): Money {
        for (condition in _conditions) {
            if (condition.isSatisfiedBy(discountFactor)) {
                return getDiscountAmount(price)
            }
        }

        return Money.ZERO;
    }

    protected abstract fun getDiscountAmount(price: Money): Money
}

package com.performance.web.api.discount.domain

import com.performance.web.api.common.domain.BusinessException
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
            if (!condition.isSatisfiedBy(discountFactor)) {
                throw BusinessException("할인 조건이 맞지 않습니다")
            }
        }

        return getDiscountAmount(price);
    }

    fun getId(): Long = _id
    fun getName(): String = _name

    protected abstract fun getDiscountAmount(price: Money): Money
}

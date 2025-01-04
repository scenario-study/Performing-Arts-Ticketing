package com.performance.web.api.performance.domain.discount

import com.performance.web.api.common.domain.Money

abstract class DefaultDiscountPolicy(
    private val conditions: List<DiscountCondition>
) : DiscountPolicy {


    override fun calculateFee(): Money {
        //    TODO("Not yet implemented")
        return Money.ZERO;
    }

    abstract fun getDiscountAmount(): Money
}

package com.performance.web.api.discount.domain

import com.performance.web.api.common.domain.Money

class PercentDiscountPolicy(
    id: Long = 0L,
    name: String,
    vararg conditions: DiscountCondition,
    percent: Double,
) : DiscountPolicy(id, name, *conditions) {

    private val _percent = percent

    override fun getDiscountAmount(price: Money): Money = price.times(_percent)
}

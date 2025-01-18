package com.performance.web.api.performance.domain.discount

import com.performance.web.api.common.domain.Money

class NoneDiscountPolicy(
    id: Long = 0L,
    name: String = "할인 미적용",
    vararg conditions: DiscountCondition
) : DiscountPolicy(id, name, *conditions) {

    override fun getDiscountAmount(price: Money): Money {
        return Money.ZERO
    }
}

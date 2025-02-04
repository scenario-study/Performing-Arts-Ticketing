package com.performance.web.api.discount.domain

import com.performance.web.api.common.domain.Money

class NoneDiscountPolicy(
    id: Long = 0L,
    name: String = "할인 미적용",
    vararg conditions: DiscountCondition,
) : DiscountPolicy(id, name, 0L, *conditions) {

    override fun getDiscountAmount(price: Money): Money = Money.ZERO
}

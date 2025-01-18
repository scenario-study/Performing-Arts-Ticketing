package com.performance.web.api.performance.domain.discount

class OffineCheckCondition : DiscountCondition {

    override fun isSatisfiedBy(discountFactor: DiscountFactor): Boolean {
        // 할인 후 오프라인 확인.
        return true
    }
}

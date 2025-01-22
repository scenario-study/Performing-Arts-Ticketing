package com.performance.web.api.discount.domain

class OfflineCheckCondition : DiscountCondition {

    override fun isSatisfiedBy(discountFactor: DiscountFactor): Boolean {
        // 할인 후 오프라인 확인.
        return true
    }
}

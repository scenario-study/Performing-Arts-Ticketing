package com.performance.web.api.performance.domain.discount

class OffineCheckCondition : DiscountCondition {

    override fun isSatisfiedBy(): Boolean {
        return true;
    }
}

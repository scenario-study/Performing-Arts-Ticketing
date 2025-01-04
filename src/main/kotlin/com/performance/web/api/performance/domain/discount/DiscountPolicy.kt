package com.performance.web.api.performance.domain.discount

import com.performance.web.api.common.domain.Money

interface DiscountPolicy {

    fun calculateFee(): Money
}

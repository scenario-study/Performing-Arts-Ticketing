package com.hunhui.ticketworld.domain.discount

import java.util.UUID

class Discount(
    val id: UUID,
    val performanceId: UUID,
    val discountName: String,
    val discountConditions: List<DiscountCondition>,
    val applyCount: DiscountApplyCount,
    val discountRate: DiscountRate,
) {
    fun isApplicable(
        roundId: UUID,
        priceId: UUID,
    ) = discountConditions.all { it.canApply(roundId, priceId) }
}

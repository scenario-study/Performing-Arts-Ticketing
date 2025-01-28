package com.hunhui.ticketworld.domain.discount

import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.common.vo.Money
import com.hunhui.ticketworld.domain.discount.exception.DiscountErrorCode
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

    fun apply(
        price: Money,
        count: Int,
    ): Money {
        validateCount(count)
        return discountRate.apply(price * count)
    }

    private fun validateCount(count: Int) {
        if (!applyCount.canApply(count)) throw BusinessException(DiscountErrorCode.CANNOT_DISCOUNT)
    }
}

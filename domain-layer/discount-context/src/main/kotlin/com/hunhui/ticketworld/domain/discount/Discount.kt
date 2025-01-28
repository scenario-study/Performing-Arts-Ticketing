package com.hunhui.ticketworld.domain.discount

import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.common.vo.Money
import com.hunhui.ticketworld.domain.discount.exception.DiscountErrorCode
import java.math.BigDecimal
import java.util.UUID

class Discount(
    val id: UUID,
    val performanceId: UUID,
    val discountName: String,
    val discountConditions: List<DiscountCondition>,
    val applyCount: DiscountApplyCount,
    val discountRate: DiscountRate,
) {
    companion object {
        val DEFAULT =
            Discount(
                id = UUID.randomUUID(),
                performanceId = UUID.randomUUID(),
                discountName = "default",
                discountConditions = emptyList(),
                applyCount = DiscountApplyInf,
                discountRate = DiscountRate(BigDecimal.ZERO),
            )
    }

    fun isApplicable(
        roundId: UUID,
        priceId: UUID,
    ) = discountConditions.all { it.canApply(roundId, priceId) }

    fun apply(
        roundId: UUID,
        priceId: UUID,
        price: Money,
        count: Int,
    ): Money {
        if (!isApplicable(roundId, priceId)) throw BusinessException(DiscountErrorCode.CANNOT_DISCOUNT)
        validateCount(count)
        return discountRate.apply(price * count)
    }

    private fun validateCount(count: Int) {
        if (!applyCount.canApply(count)) throw BusinessException(DiscountErrorCode.CANNOT_DISCOUNT)
    }
}

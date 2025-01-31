package com.hunhui.ticketworld.application.dto.request

import com.hunhui.ticketworld.domain.discount.Discount
import com.hunhui.ticketworld.domain.discount.DiscountApplyCount
import com.hunhui.ticketworld.domain.discount.DiscountApplyCountType
import com.hunhui.ticketworld.domain.discount.DiscountCondition
import com.hunhui.ticketworld.domain.discount.DiscountRate
import java.math.BigDecimal
import java.util.UUID

data class DiscountCreateRequest(
    val discountName: String,
    val discountConditions: List<DiscountCondition>,
    val applyCountType: DiscountApplyCountType,
    val applyCountAmount: Int?,
    val discountRate: BigDecimal,
) {
    fun domain(performanceId: UUID): Discount =
        Discount(
            id = UUID.randomUUID(),
            performanceId = performanceId,
            discountName = discountName,
            discountConditions = discountConditions,
            applyCount = DiscountApplyCount.create(applyCountType, applyCountAmount),
            discountRate = DiscountRate(discountRate),
        )
}

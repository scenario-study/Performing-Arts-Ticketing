package com.hunhui.ticketworld.application.dto.response

import com.hunhui.ticketworld.domain.discount.DiscountApplyCountType
import java.math.BigDecimal
import java.util.UUID

data class DiscountListResponse(
    val discountsWithPriceIdList: List<DiscountsWithPriceIdResponse>,
) {
    data class DiscountsWithPriceIdResponse(
        val performancePriceId: UUID,
        val discountResponses: List<DiscountResponse>,
    )

    data class DiscountResponse(
        val discountId: UUID,
        val discountName: String,
        val discountRate: BigDecimal,
        val applyCountType: DiscountApplyCountType,
        val applyCountAmount: Int?,
    )
}

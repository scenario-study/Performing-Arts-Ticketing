package com.hunhui.ticketworld.application.dto.request

import com.hunhui.ticketworld.application.dto.mapper.DiscountConditionMapper
import com.hunhui.ticketworld.domain.discount.Discount
import com.hunhui.ticketworld.domain.discount.DiscountApplyCountFactory
import com.hunhui.ticketworld.domain.discount.DiscountApplyCountType
import com.hunhui.ticketworld.domain.discount.DiscountConditionType
import com.hunhui.ticketworld.domain.discount.DiscountRate
import java.math.BigDecimal
import java.util.UUID

data class DiscountCreateRequest(
    val discountName: String,
    val discountConditions: List<DiscountConditionRequest>,
    val applyCountType: DiscountApplyCountType,
    val applyCountAmount: Int?,
    val discountRate: BigDecimal,
) {
    fun domain(performanceId: UUID): Discount =
        Discount(
            id = UUID.randomUUID(),
            performanceId = performanceId,
            discountName = discountName,
            discountConditions =
                discountConditions.map {
                    DiscountConditionMapper.toDomain(it.type, it.data)
                },
            applyCount = DiscountApplyCountFactory.create(applyCountType, applyCountAmount),
            discountRate = DiscountRate(discountRate),
        )

    data class DiscountConditionRequest(
        val type: DiscountConditionType,
        val data: Map<String, Any>,
    )
}

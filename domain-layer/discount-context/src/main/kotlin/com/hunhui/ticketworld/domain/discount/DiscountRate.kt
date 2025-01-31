package com.hunhui.ticketworld.domain.discount

import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.common.vo.Money
import com.hunhui.ticketworld.domain.discount.exception.DiscountErrorCode
import java.math.BigDecimal

data class DiscountRate(
    val rate: BigDecimal,
) {
    init {
        if (rate < BigDecimal(0) || rate > BigDecimal(1)) throw BusinessException(DiscountErrorCode.INVALID_DISCOUNT_RATE)
    }

    fun apply(price: Money): Money = Money(BigDecimal(price.amount).multiply(BigDecimal(1) - rate).toLong())
}

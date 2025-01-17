package com.hunhui.ticketworld.common.vo

import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.common.error.GlobalErrorCode.MONEY_IS_NEGATIVE

data class Money(
    val amount: Long,
) : Comparable<Money> {
    init {
        if (amount < 0) throw BusinessException(MONEY_IS_NEGATIVE)
    }

    override fun compareTo(other: Money): Int = this.amount.compareTo(other.amount)
}

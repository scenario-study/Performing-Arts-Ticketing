package com.hunhui.ticketworld.common.vo

import com.hunhui.ticketworld.common.error.InvalidMoneyException

data class Money(
    val amount: Long,
) : Comparable<Money> {
    init {
        require(amount >= 0) { throw InvalidMoneyException() }
    }

    override fun compareTo(other: Money): Int = this.amount.compareTo(other.amount)
}

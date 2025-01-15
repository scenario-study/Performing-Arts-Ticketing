package com.hunhui.ticketworld.common.vo

data class Money(
    val amount: Long,
) : Comparable<Money> {
    init {
        require(amount >= 0) { "Amount must be greater than or equal to 0" }
    }

    override fun compareTo(other: Money): Int {
        return this.amount.compareTo(other.amount)
    }
}

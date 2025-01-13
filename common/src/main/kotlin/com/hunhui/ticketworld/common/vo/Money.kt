package com.hunhui.ticketworld.common.vo

data class Money(
    val amount: Long,
) {
    init {
        require(amount >= 0) { "Amount must be greater than or equal to 0" }
    }
}

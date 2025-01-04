package com.performance.web.api.common.domain

import java.math.BigDecimal

data class Money(
    private val amount: BigDecimal
) {

    companion object {
        val ZERO = this.of(0)

        fun of(amount: Long): Money {
            return Money(BigDecimal(amount))
        }

        fun of(amount: Double): Money {
            return Money(BigDecimal(amount))
        }
    }


    fun plus(money: Money): Money {
        return Money(amount.add(money.amount))
    }

    fun minus(money: Money): Money {
        return Money(amount.subtract(money.amount))
    }

}

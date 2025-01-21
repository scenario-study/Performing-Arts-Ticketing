package com.performance.web.api.reservation.domain

import com.performance.web.api.common.domain.Money
import com.performance.web.api.discount.domain.DiscountFactor
import com.performance.web.api.discount.domain.DiscountPolicy

class SeatClass(
    price: Money,
) {

    private val _price: Money = price

    fun getPrice(): Money {
        return _price
    }

    fun calculateTotalAmount(discount: DiscountPolicy, discountFactor: DiscountFactor): Money {
        return _price.minus(discount.calculateFee(_price, discountFactor))
    }
}

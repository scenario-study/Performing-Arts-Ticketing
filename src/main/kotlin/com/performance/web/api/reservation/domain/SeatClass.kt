package com.performance.web.api.reservation.domain

import com.performance.web.api.common.domain.Money
import com.performance.web.api.discount.domain.DiscountFactor
import com.performance.web.api.discount.domain.DiscountPolicy

class SeatClass(
    price: Money,
    classType: String,
) {

    private val _price: Money = price
    private val _classType: String = classType

    fun getPrice(): Money = _price
    fun getClassType() = _classType


    fun calculateTotalAmount(discount: DiscountPolicy, discountFactor: DiscountFactor): Money {
        return _price.minus(discount.calculateFee(_price, discountFactor))
    }
}

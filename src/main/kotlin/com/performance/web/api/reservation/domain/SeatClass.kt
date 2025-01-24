package com.performance.web.api.reservation.domain

import com.performance.web.api.common.domain.BusinessException
import com.performance.web.api.common.domain.Money
import com.performance.web.api.discount.domain.DiscountFactor
import com.performance.web.api.discount.domain.DiscountPolicy

class SeatClass(
    price: Money,
    classType: String,
    discountPolicies: List<DiscountPolicy> = mutableListOf()
) {

    private val _price: Money = price
    private val _classType: String = classType
    private val _discountPolicies: List<DiscountPolicy> = discountPolicies

    fun getPrice(): Money = _price

    fun getClassType() = _classType

    fun issueTicket(seat: Seat, discountFactor: DiscountFactor, discountPolicyId: Long?): Ticket {
        val discountPolicy = discountPolicyId?.let { findDiscountPolicyById(discountPolicyId) } ?: DiscountPolicy.none()

        return Ticket(
            totalAmount = _price.minus(discountPolicy.calculateFee(_price, discountFactor)),
            regularPrice = _price,
            seat = seat,
            appliedDiscountPolicy = discountPolicy,
        )
    }

    private fun findDiscountPolicyById(id: Long): DiscountPolicy {
        return this._discountPolicies.find { it.getId() == id } ?: throw BusinessException("잘못된 discountPolicyId 입니다")
    }
}

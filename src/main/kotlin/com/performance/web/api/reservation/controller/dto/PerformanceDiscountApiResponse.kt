package com.performance.web.api.reservation.controller.dto

import com.performance.web.api.common.domain.Money
import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.reservation.domain.SeatClass

data class PerformanceDiscountApiResponse(
    val seatClassType: String,
    val price: Long,
    val discountInfo: List<DiscountInfoApiResponse>
) {

    companion object {

        fun from(seatClass: SeatClass): PerformanceDiscountApiResponse {
            return PerformanceDiscountApiResponse(
                seatClassType = seatClass.getClassType(),
                price = seatClass.getPrice().longValue(),
                discountInfo = seatClass.getDiscountPolicies()
                    .map { DiscountInfoApiResponse.from(it, seatClass.getPrice()) },
            )
        }
    }

    data class DiscountInfoApiResponse(
        val id: Long,
        val name: String,
        val fee: Long
    ) {

        companion object {
            fun from(discountPolicy: DiscountPolicy, price: Money): DiscountInfoApiResponse {
                return DiscountInfoApiResponse(
                    id = discountPolicy.getId(),
                    name = discountPolicy.getName(),
                    fee = discountPolicy.calculateDiscountAmountWithoutCondition(price).longValue(),
                )
            }
        }
    }

}

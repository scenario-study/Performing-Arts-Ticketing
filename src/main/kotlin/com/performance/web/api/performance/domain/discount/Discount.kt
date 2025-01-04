package com.performance.web.api.performance.domain.discount

class Discount(
    val id: Long,
    val name : String, // 할인 이름
    val discountPolicy: DiscountPolicy
) {

}

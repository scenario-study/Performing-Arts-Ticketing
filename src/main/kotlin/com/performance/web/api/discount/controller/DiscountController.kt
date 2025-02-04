package com.performance.web.api.discount.controller

import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.discount.service.DiscountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/discounts")
class DiscountController(
    private val discountService: DiscountService
) {


    @GetMapping("/all")
    fun allDiscounts():ResponseEntity<DiscountPolicy>{
        val result = discountService.findById(1L);
        return ResponseEntity.ok(result)
    }
}

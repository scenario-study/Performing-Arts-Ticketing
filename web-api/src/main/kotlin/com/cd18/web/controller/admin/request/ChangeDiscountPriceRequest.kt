package com.cd18.web.controller.admin.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

data class ChangeDiscountPriceRequest(
    @field:Schema(description = "티켓 할인 금액", example = "1000")
    @field:Min(1, message = "할인 금액은 1원 이상 금액이 입력되어야 합니다.")
    val discountPrice: Int,
)

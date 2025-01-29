package com.cd18.web.controller.admin

import com.cd18.application.performance.PerformanceInfoService
import com.cd18.common.http.response.ApiResponse
import com.cd18.common.http.response.ApiStatus
import com.cd18.domain.performance.enums.PerformanceInfoSuccessCode
import com.cd18.web.controller.admin.request.ChangeDiscountPriceRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// TODO : 관리자 권한 인증 로직 추가 필요
@RestController
@RequestMapping("/admin/performance")
@Tag(name = "[관리자용] 공연 정보", description = "공연 정보 APIs")
class AdminPerformanceInfoController(
    private val performanceInfoService: PerformanceInfoService,
) {
    @PatchMapping("/{id}/discount-price")
    @Operation(
        summary = "공연 할인 금액 변경",
        description = "공연 할인 금액을 변경합니다.",
    )
    fun changeDiscountPrice(
        @Parameter(description = "공연 ID", required = true, example = "1") @PathVariable id: Long,
        @Valid @RequestBody request: ChangeDiscountPriceRequest,
    ): ApiResponse<Unit> {
        performanceInfoService.changeDiscountPrice(id, request.discountPrice).getOrThrow()
        return ApiResponse(status = ApiStatus.of(PerformanceInfoSuccessCode.CHANGE_DISCOUNT_PRICE))
    }
}

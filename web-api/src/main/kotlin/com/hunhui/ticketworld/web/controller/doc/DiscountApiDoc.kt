package com.hunhui.ticketworld.web.controller.doc

import com.hunhui.ticketworld.application.dto.request.DiscountCreateRequest
import com.hunhui.ticketworld.application.dto.request.DiscountFindRequest
import com.hunhui.ticketworld.application.dto.response.DiscountListResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID

@Tag(name = "Discount", description = "할인 관련 API")
interface DiscountApiDoc {
    @Operation(summary = "할인 생성 API")
    fun createDiscount(
        @PathVariable performanceId: UUID,
        @RequestBody discountCreateRequest: DiscountCreateRequest,
    ): ResponseEntity<Unit>

    @Operation(summary = "할인 목록 조회 API")
    fun findAllDiscount(
        @PathVariable performanceId: UUID,
        @RequestBody discountFindRequest: DiscountFindRequest,
    ): ResponseEntity<DiscountListResponse>
}

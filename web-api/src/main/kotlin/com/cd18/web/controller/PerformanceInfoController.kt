package com.cd18.web.controller

import com.cd18.application.performance.PerformanceInfoService
import com.cd18.common.http.response.ApiResponse
import com.cd18.web.controller.request.PageRequest
import com.cd18.web.controller.response.PerformanceInfoDetailResponse
import com.cd18.web.controller.response.PerformanceInfoListResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/performance")
@Tag(name = "공연 정보", description = "공연 정보 APIs")
class PerformanceInfoController(
    private val performanceInfoService: PerformanceInfoService,
) {
    @GetMapping("")
    @Operation(
        summary = "공연 정보 리스트",
        description = "공연 정보 리스트를 조회합니다.",
    )
    fun getInfo(
        @Parameter(description = "페이지 정보")
        pageRequest: PageRequest,
    ): ApiResponse<PerformanceInfoListResponse> {
        val data = performanceInfoService.getList(pageRequest.toPageParam()).getOrThrow()
        return ApiResponse(result = PerformanceInfoListResponse.of(data))
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "공연 상세 정보",
        description = "공연 상세 정보를 조회합니다.",
    )
    fun getDetailInfo(
        @Parameter(description = "공연 ID", required = true, example = "1")
        @PathVariable id: Long,
    ): ApiResponse<PerformanceInfoDetailResponse> {
        val data = performanceInfoService.getById(id).getOrThrow()
        return ApiResponse(result = PerformanceInfoDetailResponse.of(data))
    }
}

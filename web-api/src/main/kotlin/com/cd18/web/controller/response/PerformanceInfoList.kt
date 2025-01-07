package com.cd18.web.controller.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "공연 정보 목록 응답")
data class PerformanceInfoList(
    @Schema(description = "공연 요약 정보 목록", required = true)
    val performanceList: List<PerformanceSummaryInfo>,
) {
    @Schema(description = "공연 요약 정보 DTO")
    data class PerformanceSummaryInfo(
        @Schema(description = "공연 ID", example = "1")
        val performanceId: Long,
        @Schema(description = "공연 이름", example = "레미제라블")
        val performanceName: String,
        @Schema(description = "공연 장소", example = "블루스퀘어 신한카드홀")
        val performanceVenue: String,
        @Schema(description = "공연 티켓 가격", example = "50000")
        val performanceOriginPrice: Int,
        @Schema(description = "공연 할인 가격", example = "45000")
        val performanceDiscountPrice: Int,
        @Schema(description = "공연 할인율", example = "10")
        val performanceDiscountRate: Int,
        @Schema(description = "공연 시작일", example = "2025-03-01")
        val startDate: String,
        @Schema(description = "공연 종료일", example = "2025-05-05")
        val endDate: String,
    )
}

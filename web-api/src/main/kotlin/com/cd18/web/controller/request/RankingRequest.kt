package com.cd18.web.controller.request

import com.cd18.domain.metrics.enums.RankingPeriod
import io.swagger.v3.oas.annotations.Parameter

data class RankingRequest(
    @Parameter(description = "랭킹 타입(HOURLY | DAILY | WEEKLY)", example = "DAILY")
    val period: RankingPeriod = RankingPeriod.DAILY,
)

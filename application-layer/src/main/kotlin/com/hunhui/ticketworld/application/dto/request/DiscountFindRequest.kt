package com.hunhui.ticketworld.application.dto.request

import java.util.UUID

data class DiscountFindRequest(
    val performanceRoundId: UUID,
    val reservePriceIds: List<UUID>,
)

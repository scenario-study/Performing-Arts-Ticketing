package com.performance.web.api.performance.domain

import java.time.LocalDateTime

class Session(
    val performance: Performance,
    val time: LocalDateTime,
    val currentSeatInfo: List<SeatInfo>
) {
}

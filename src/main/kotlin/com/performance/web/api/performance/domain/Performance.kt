package com.performance.web.api.performance.domain

import java.time.Period

class Performance(
    val id: Long,
    val name: String,
    val period: Period,
    val venue: Venue,
    val seats: List<Seat>
) {
}

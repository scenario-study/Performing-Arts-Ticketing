package com.performance.web.api.seat.domain

data class SeatPosition(
    val row: Int,
    val column: Int,
    val floor: Int = 1, // default
)

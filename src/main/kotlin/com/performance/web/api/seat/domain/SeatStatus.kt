package com.performance.web.api.seat.domain

enum class SeatStatus {
    UN_RESERVED,
    RESERVED,
    ;

    fun canReserve(): Boolean = this == UN_RESERVED
}

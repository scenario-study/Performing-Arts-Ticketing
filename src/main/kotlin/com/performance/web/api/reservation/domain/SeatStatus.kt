package com.performance.web.api.reservation.domain

enum class SeatStatus {
    UN_RESERVED, RESERVED;

    fun canReserve(): Boolean {
        return this == UN_RESERVED
    }

}

package com.performance.web.api.performance.domain

enum class SeatStatus {
    UN_RESERVED, RESERVED;

    fun canReserve(): Boolean {
        return this == UN_RESERVED
    }

}

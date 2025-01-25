package com.performance.web.api.reservation.infrastructure.jpa

import com.performance.web.api.reservation.domain.SeatStatus

enum class SeatStatusEntity {
    UN_RESERVE,
    RESERVED, ;

    fun toDomain(): SeatStatus {
        return when (this) {
            UN_RESERVE -> SeatStatus.UN_RESERVED
            RESERVED -> SeatStatus.RESERVED
        }
    }

    companion object {
        fun fromDomain(seatStatus: SeatStatus): SeatStatusEntity {
            return when (seatStatus) {
                SeatStatus.UN_RESERVED -> UN_RESERVE
                SeatStatus.RESERVED -> RESERVED
            }
        }
    }
}

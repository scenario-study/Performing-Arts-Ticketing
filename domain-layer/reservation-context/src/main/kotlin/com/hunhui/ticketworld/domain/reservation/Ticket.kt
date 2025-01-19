package com.hunhui.ticketworld.domain.reservation

import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.domain.reservation.exception.ReservationErrorCode
import java.time.LocalDateTime
import java.util.UUID

class Ticket(
    val id: UUID,
    val roundId: UUID,
    val seatAreaId: UUID,
    val seatId: UUID,
    val tempUserId: UUID?,
    val tempReservationExpireTime: LocalDateTime?,
    val isPaid: Boolean,
) {
    companion object {
        private const val EXPIRE_MINUTES = 7L

        fun getExpireTime(): LocalDateTime = LocalDateTime.now().plusMinutes(EXPIRE_MINUTES)
    }

    val canReserve: Boolean
        get() = isTempReservationExpired && !isPaid

    fun tempReserve(userId: UUID): Ticket {
        if (!canReserve) throw BusinessException(ReservationErrorCode.CANNOT_RESERVE)
        return Ticket(
            id = id,
            roundId = roundId,
            seatAreaId = seatAreaId,
            seatId = seatId,
            tempUserId = userId,
            tempReservationExpireTime = getExpireTime(),
            isPaid = isPaid,
        )
    }

    /** 만료시간이 없거나 이미 지났으면 true */
    private val isTempReservationExpired: Boolean
        get() = tempReservationExpireTime?.isBefore(LocalDateTime.now()) ?: true
}

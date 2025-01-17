package com.hunhui.ticketworld.application.dto.response

import com.hunhui.ticketworld.domain.reservation.ReservationStatus
import java.util.UUID

data class ReservationStatusListResponse(
    val reservationStatusList: List<ReservationStatusResponse>,
) {
    companion object {
        fun from(reservationStatusList: List<ReservationStatus>): ReservationStatusListResponse =
            ReservationStatusListResponse(
                reservationStatusList =
                    reservationStatusList.map {
                        ReservationStatusResponse(
                            id = it.id,
                            seatId = it.seatId,
                            canReserve = it.canReserve,
                        )
                    },
            )
    }

    data class ReservationStatusResponse(
        val id: UUID,
        val seatId: UUID,
        val canReserve: Boolean,
    )
}

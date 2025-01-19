package com.hunhui.ticketworld.application.dto.response

import com.hunhui.ticketworld.domain.seat.SeatArea
import java.util.UUID

data class SeatAreasResponse(
    val seatAreas: List<SeatAreaResponse>,
) {
    companion object {
        fun from(seatAreas: List<SeatArea>): SeatAreasResponse =
            SeatAreasResponse(
                seatAreas.map { seatArea ->
                    SeatAreaResponse(
                        id = seatArea.id,
                        floorName = seatArea.floorName,
                        areaName = seatArea.areaName,
                        width = seatArea.width,
                        height = seatArea.height,
                        seats =
                            seatArea.seats.map { seat ->
                                SeatResponse(
                                    id = seat.id,
                                    performancePriceId = seat.performancePriceId,
                                    seatName = seat.seatName,
                                    x = seat.x,
                                    y = seat.y,
                                )
                            },
                    )
                },
            )
    }

    data class SeatAreaResponse(
        val id: UUID,
        val floorName: String,
        val areaName: String,
        val width: Int,
        val height: Int,
        val seats: List<SeatResponse>,
    )

    data class SeatResponse(
        val id: UUID,
        val performancePriceId: UUID,
        val seatName: String,
        val x: Int,
        val y: Int,
    )
}

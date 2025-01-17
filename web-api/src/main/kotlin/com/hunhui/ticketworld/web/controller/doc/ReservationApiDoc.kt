package com.hunhui.ticketworld.web.controller.doc

import com.hunhui.ticketworld.application.dto.response.ReservationStatusListResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

@Tag(name = "Reservation", description = "예매 관련 API")
interface ReservationApiDoc {
    @Operation(summary = "좌석 상태 목록 API")
    fun findAllReservationStatus(
        @RequestParam roundId: UUID,
        @RequestParam areaId: UUID,
    ): ResponseEntity<ReservationStatusListResponse>

    @Operation(summary = "임시 예매 API")
    fun tempReserve(
        @RequestParam reservationStatusId: UUID,
        @RequestParam userId: UUID,
    ): ResponseEntity<Unit>
}

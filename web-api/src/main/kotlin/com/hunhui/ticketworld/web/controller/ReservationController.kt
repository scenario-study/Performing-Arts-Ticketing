package com.hunhui.ticketworld.web.controller

import com.hunhui.ticketworld.application.ReservationService
import com.hunhui.ticketworld.application.dto.response.TicketListResponse
import com.hunhui.ticketworld.web.controller.doc.ReservationApiDoc
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/reservation")
class ReservationController(
    private val reservationService: ReservationService,
) : ReservationApiDoc {
    @GetMapping("/status")
    override fun findAllTickets(
        @RequestParam roundId: UUID,
        @RequestParam areaId: UUID,
    ): ResponseEntity<TicketListResponse> = ResponseEntity.ok(reservationService.findAllTicket(roundId, areaId))

    @PatchMapping("/temp-reserve")
    override fun tempReserve(
        @RequestParam ticketId: UUID,
        @RequestParam userId: UUID,
    ): ResponseEntity<Unit> = ResponseEntity.ok(reservationService.tempReserve(ticketId, userId))
}

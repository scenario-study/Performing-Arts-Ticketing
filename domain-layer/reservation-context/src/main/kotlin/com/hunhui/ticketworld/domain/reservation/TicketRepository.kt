package com.hunhui.ticketworld.domain.reservation

import java.util.UUID

interface TicketRepository {
    fun getById(id: UUID): Ticket

    fun findAllByRoundIdAndAreaId(
        performanceRoundId: UUID,
        seatAreaId: UUID,
    ): List<Ticket>

    fun save(ticket: Ticket)

    fun saveAll(tickets: List<Ticket>)
}

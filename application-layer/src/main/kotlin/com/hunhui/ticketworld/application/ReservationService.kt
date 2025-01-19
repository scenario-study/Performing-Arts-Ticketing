package com.hunhui.ticketworld.application

import com.hunhui.ticketworld.application.dto.response.TicketListResponse
import com.hunhui.ticketworld.domain.reservation.Ticket
import com.hunhui.ticketworld.domain.reservation.TicketRepository
import com.hunhui.ticketworld.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ReservationService(
    private val ticketRepository: TicketRepository,
    private val userRepository: UserRepository,
) {
    fun findAllTicket(
        roundId: UUID,
        areaId: UUID,
    ): TicketListResponse {
        val ticketList: List<Ticket> = ticketRepository.findAllByRoundIdAndAreaId(roundId, areaId)
        return TicketListResponse.from(ticketList)
    }

    @Transactional
    fun tempReserve(
        ticketId: UUID,
        userId: UUID,
    ) {
        userRepository.getById(userId)
        val ticket: Ticket = ticketRepository.getById(ticketId)
        val updatedTicket: Ticket = ticket.tempReserve(userId)
        ticketRepository.save(updatedTicket)
    }
}

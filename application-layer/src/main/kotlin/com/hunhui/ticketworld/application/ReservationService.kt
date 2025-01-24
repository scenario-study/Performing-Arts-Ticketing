package com.hunhui.ticketworld.application

import com.hunhui.ticketworld.application.dto.request.TempReserveRequest
import com.hunhui.ticketworld.application.dto.response.TicketListResponse
import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.domain.performance.PerformanceRepository
import com.hunhui.ticketworld.domain.reservation.Ticket
import com.hunhui.ticketworld.domain.reservation.TicketRepository
import com.hunhui.ticketworld.domain.reservation.exception.ReservationErrorCode
import com.hunhui.ticketworld.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ReservationService(
    private val performanceRepository: PerformanceRepository,
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
    fun tempReserve(tempReserveRequest: TempReserveRequest) {
        tempReserveRequest.validate()
        val updatedTickets: List<Ticket> =
            tempReserveRequest.ticketIdList.map { ticketId ->
                val ticket: Ticket = ticketRepository.getById(ticketId)
                ticket.tempReserve(tempReserveRequest.userId)
            }
        ticketRepository.saveAll(updatedTickets)
    }

    private fun TempReserveRequest.validate() {
        userRepository.getById(userId)
        val reserveCount = performanceRepository.getById(performanceId).reserveCount
        if (reserveCount < ticketIdList.size) throw BusinessException(ReservationErrorCode.RESERVE_COUNT_EXCEED)
    }
}

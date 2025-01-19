package com.hunhui.ticketworld.infra.jpa.repository

import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.domain.reservation.Ticket
import com.hunhui.ticketworld.domain.reservation.TicketRepository
import com.hunhui.ticketworld.domain.reservation.exception.ReservationErrorCode
import com.hunhui.ticketworld.infra.jpa.entity.TicketEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
internal class TicketRepositoryImpl(
    private val ticketJpaRepository: TicketJpaRepository,
) : TicketRepository {
    override fun getById(id: UUID): Ticket =
        ticketJpaRepository.findByIdOrNull(id)?.domain ?: throw BusinessException(ReservationErrorCode.CANNOT_RESERVE)

    override fun findAllByRoundIdAndAreaId(
        performanceRoundId: UUID,
        seatAreaId: UUID,
    ): List<Ticket> =
        ticketJpaRepository
            .findAllByPerformanceRoundIdAndSeatAreaId(performanceRoundId, seatAreaId)
            .map { it.domain }

    override fun save(ticket: Ticket) {
        ticketJpaRepository.save(ticket.entity)
    }

    override fun saveAll(tickets: List<Ticket>) {
        ticketJpaRepository.saveAll(tickets.map { it.entity })
    }

    private val Ticket.entity: TicketEntity
        get() =
            TicketEntity(
                id = id,
                performanceRoundId = roundId,
                seatAreaId = seatAreaId,
                seatId = seatId,
                userId = tempUserId,
                reservationExpireTime = tempReservationExpireTime,
                isPaid = isPaid,
            )

    private val TicketEntity.domain: Ticket
        get() =
            Ticket(
                id = id,
                roundId = performanceRoundId,
                seatAreaId = seatAreaId,
                seatId = seatId,
                tempUserId = userId,
                tempReservationExpireTime = reservationExpireTime,
                isPaid = isPaid,
            )
}

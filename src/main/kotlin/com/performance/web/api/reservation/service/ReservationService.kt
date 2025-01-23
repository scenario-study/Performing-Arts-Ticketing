package com.performance.web.api.reservation.service

import com.performance.web.api.common.domain.ResourceNotFoundException
import com.performance.web.api.discount.domain.DiscountFactor
import com.performance.web.api.reservation.domain.Reservation
import com.performance.web.api.reservation.domain.ReservationRepository
import com.performance.web.api.reservation.domain.SessionRepository
import com.performance.web.api.reservation.service.dto.ReservationCommand
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReservationService(
    private val sessionRepository: SessionRepository,
    private val reservationRepository: ReservationRepository,
) {

    fun reserve(reservationCommand: ReservationCommand): Reservation {
        val (customer, sessionId, seatCommands) = reservationCommand

        val session =
            sessionRepository.findByIdWithSeatAnsClassAndPerformance(sessionId)
                .orElseThrow { throw ResourceNotFoundException("$sessionId id의 session을  찾을 수 없습니다") }

        val reservation =
            session.reserve(
                customer = customer,
                seatCommands = seatCommands.map { it.toEntityCommand() }.toMutableList(),
                discountFactor = DiscountFactor(LocalDateTime.now(), seatCommands.size),
            )

        return reservationRepository.save(reservation)
    }


    fun findById(reservationId: Long): Reservation {
        val reservation = reservationRepository.findById(reservationId)
            .orElseThrow { throw ResourceNotFoundException("${reservationId}의 Reservation을 찾을 수 없습니다.") }

        return reservation
    }
}

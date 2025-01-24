package com.hunhui.ticketworld.application

import com.hunhui.ticketworld.application.dto.request.TempReserveRequest
import com.hunhui.ticketworld.application.dto.response.ReservationListResponse
import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.domain.performance.PerformanceRepository
import com.hunhui.ticketworld.domain.reservation.Reservation
import com.hunhui.ticketworld.domain.reservation.ReservationRepository
import com.hunhui.ticketworld.domain.reservation.exception.ReservationErrorCode
import com.hunhui.ticketworld.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ReservationService(
    private val performanceRepository: PerformanceRepository,
    private val reservationRepository: ReservationRepository,
    private val userRepository: UserRepository,
) {
    fun findAll(
        roundId: UUID,
        areaId: UUID,
    ): ReservationListResponse {
        val reservationList: List<Reservation> = reservationRepository.findAllByRoundIdAndAreaId(roundId, areaId)
        return ReservationListResponse.from(reservationList)
    }

    @Transactional
    fun tempReserve(tempReserveRequest: TempReserveRequest) {
        tempReserveRequest.validate()
        val updatedReservations: List<Reservation> =
            tempReserveRequest.ticketIdList.map { ticketId ->
                val reservation: Reservation = reservationRepository.getById(ticketId)
                reservation.tempReserve(tempReserveRequest.userId)
            }
        reservationRepository.saveAll(updatedReservations)
    }

    private fun TempReserveRequest.validate() {
        userRepository.getById(userId)
        val reserveCount = performanceRepository.getById(performanceId).reserveCount
        if (reserveCount < ticketIdList.size) throw BusinessException(ReservationErrorCode.RESERVE_COUNT_EXCEED)
    }
}

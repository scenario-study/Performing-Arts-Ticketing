package com.hunhui.ticketworld.application

import com.hunhui.ticketworld.application.dto.response.ReservationStatusListResponse
import com.hunhui.ticketworld.domain.reservation.ReservationStatus
import com.hunhui.ticketworld.domain.reservation.ReservationStatusRepository
import com.hunhui.ticketworld.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ReservationService(
    private val reservationStatusRepository: ReservationStatusRepository,
    private val userRepository: UserRepository,
) {
    fun findAllReservationStatus(
        roundId: UUID,
        areaId: UUID,
    ): ReservationStatusListResponse {
        val reservationStatusList: List<ReservationStatus> = reservationStatusRepository.findAllByRoundIdAndAreaId(roundId, areaId)
        return ReservationStatusListResponse.from(reservationStatusList)
    }

    @Transactional
    fun tempReserve(
        reservationStatusId: UUID,
        userId: UUID,
    ) {
        userRepository.getById(userId)
        val reservationStatus: ReservationStatus = reservationStatusRepository.getById(reservationStatusId)
        val updatedReservationStatus: ReservationStatus = reservationStatus.tempReserve(userId)
        reservationStatusRepository.save(updatedReservationStatus)
    }
}

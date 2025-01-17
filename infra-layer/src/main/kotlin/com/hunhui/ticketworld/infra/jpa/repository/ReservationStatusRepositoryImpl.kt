package com.hunhui.ticketworld.infra.jpa.repository

import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.domain.reservation.ReservationStatus
import com.hunhui.ticketworld.domain.reservation.ReservationStatusRepository
import com.hunhui.ticketworld.domain.reservation.exception.ReservationErrorCode
import com.hunhui.ticketworld.infra.jpa.entity.ReservationStatusEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
internal class ReservationStatusRepositoryImpl(
    private val reservationStatusJpaRepository: ReservationStatusJpaRepository,
) : ReservationStatusRepository {
    override fun getById(id: UUID): ReservationStatus =
        reservationStatusJpaRepository.findByIdOrNull(id)?.domain ?: throw BusinessException(ReservationErrorCode.CANNOT_RESERVE)

    override fun findAllByRoundIdAndAreaId(
        performanceRoundId: UUID,
        seatAreaId: UUID,
    ): List<ReservationStatus> =
        reservationStatusJpaRepository
            .findAllByPerformanceRoundIdAndSeatAreaId(performanceRoundId, seatAreaId)
            .map { it.domain }

    override fun save(reservationStatus: ReservationStatus) {
        reservationStatusJpaRepository.save(reservationStatus.entity)
    }

    override fun saveAll(reservationStatuses: List<ReservationStatus>) {
        reservationStatusJpaRepository.saveAll(reservationStatuses.map { it.entity })
    }

    private val ReservationStatus.entity: ReservationStatusEntity
        get() =
            ReservationStatusEntity(
                id = id,
                performanceRoundId = roundId,
                seatAreaId = seatAreaId,
                seatId = seatId,
                userId = tempUserId,
                reservationExpireTime = tempReservationExpireTime,
                isPaid = isPaid,
            )

    private val ReservationStatusEntity.domain: ReservationStatus
        get() =
            ReservationStatus(
                id = id,
                roundId = performanceRoundId,
                seatAreaId = seatAreaId,
                seatId = seatId,
                tempUserId = userId,
                tempReservationExpireTime = reservationExpireTime,
                isPaid = isPaid,
            )
}

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
            .findAllByRoundIdAndSeatId(performanceRoundId, seatAreaId)
            .map { it.domain }

    override fun save(reservationStatus: ReservationStatus) {
        reservationStatusJpaRepository.save(reservationStatus.entity)
    }

    private val ReservationStatus.entity: ReservationStatusEntity
        get() =
            ReservationStatusEntity(
                id = id,
                roundId = roundId,
                seatId = seatId,
                tempUserId = tempUserId,
                tempReservationExpireTime = tempReservationExpireTime,
                isPaid = isPaid,
            )

    private val ReservationStatusEntity.domain: ReservationStatus
        get() =
            ReservationStatus(
                id = id,
                roundId = roundId,
                seatId = seatId,
                tempUserId = tempUserId,
                tempReservationExpireTime = tempReservationExpireTime,
                isPaid = isPaid,
            )
}

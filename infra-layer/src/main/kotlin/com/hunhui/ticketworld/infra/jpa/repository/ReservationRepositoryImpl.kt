package com.hunhui.ticketworld.infra.jpa.repository

import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.domain.reservation.Reservation
import com.hunhui.ticketworld.domain.reservation.ReservationRepository
import com.hunhui.ticketworld.domain.reservation.exception.ReservationErrorCode
import com.hunhui.ticketworld.infra.jpa.entity.ReservationEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
internal class ReservationRepositoryImpl(
    private val reservationJpaRepository: ReservationJpaRepository,
) : ReservationRepository {
    override fun getById(id: UUID): Reservation =
        reservationJpaRepository.findByIdOrNull(id)?.domain ?: throw BusinessException(ReservationErrorCode.CANNOT_RESERVE)

    override fun findAllByRoundIdAndAreaId(
        performanceRoundId: UUID,
        seatAreaId: UUID,
    ): List<Reservation> =
        reservationJpaRepository
            .findAllByPerformanceRoundIdAndSeatAreaId(performanceRoundId, seatAreaId)
            .map { it.domain }

    override fun save(reservation: Reservation) {
        reservationJpaRepository.save(reservation.entity)
    }

    override fun saveAll(reservations: List<Reservation>) {
        reservationJpaRepository.saveAll(reservations.map { it.entity })
    }

    private val Reservation.entity: ReservationEntity
        get() =
            ReservationEntity(
                id = id,
                performanceRoundId = roundId,
                seatAreaId = seatAreaId,
                seatId = seatId,
                userId = tempUserId,
                paymentId = paymentId,
                reservationExpireTime = tempReservationExpireTime,
            )

    private val ReservationEntity.domain: Reservation
        get() =
            Reservation(
                id = id,
                roundId = performanceRoundId,
                seatAreaId = seatAreaId,
                seatId = seatId,
                tempUserId = userId,
                paymentId = paymentId,
                tempReservationExpireTime = reservationExpireTime,
            )
}

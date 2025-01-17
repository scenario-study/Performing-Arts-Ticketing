package com.hunhui.ticketworld.infra.jpa.repository

import com.hunhui.ticketworld.infra.jpa.entity.ReservationStatusEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

internal interface ReservationStatusJpaRepository : JpaRepository<ReservationStatusEntity, UUID> {
    fun findAllByRoundIdAndSeatId(
        roundId: UUID,
        seatId: UUID,
    ): List<ReservationStatusEntity>
}

package com.hunhui.ticketworld.infra.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "reservation_status")
internal class ReservationStatusEntity(
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),
    @Column(name = "roundId", nullable = false)
    val roundId: UUID,
    @Column(name = "seatId", nullable = false)
    val seatId: UUID,
    @Column(name = "tempUserId", nullable = true)
    val tempUserId: UUID?,
    @Column(name = "tempReservationExpireTime", nullable = true)
    val tempReservationExpireTime: LocalDateTime?,
    @Column(name = "isPaid", nullable = false)
    val isPaid: Boolean,
) : BaseTimeEntity()

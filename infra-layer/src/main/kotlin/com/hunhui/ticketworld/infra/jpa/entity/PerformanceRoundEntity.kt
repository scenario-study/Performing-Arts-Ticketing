package com.hunhui.ticketworld.infra.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "performance_round")
internal class PerformanceRoundEntity(
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),
    @Column(name = "roundStartTime", nullable = false)
    val roundStartTime: LocalDateTime,
    @Column(name = "reservationStartTime", nullable = false)
    val reservationStartTime: LocalDateTime,
    @Column(name = "reservationEndTime", nullable = false)
    val reservationEndTime: LocalDateTime,
    @Column(name = "performanceId", nullable = false)
    val performanceId: UUID,
) : BaseTimeEntity()

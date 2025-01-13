package com.hunhui.ticketworld.infra.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "performanceRound")
internal class PerformanceRoundEntity(
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),

    @Column(name = "performanceDateTime", nullable = false)
    val performanceDateTime: LocalDateTime,

    @Column(name = "reservationStartDateTime", nullable = false)
    val reservationStartDateTime: LocalDateTime,

    @Column(name = "reservationFinishDateTime", nullable = false)
    val reservationFinishDateTime: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performanceId")
    val performance: PerformanceEntity? = null
) : BaseTimeEntity()

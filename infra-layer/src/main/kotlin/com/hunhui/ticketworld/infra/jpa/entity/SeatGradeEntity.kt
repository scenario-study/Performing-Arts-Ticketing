package com.hunhui.ticketworld.infra.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "seat_grade")
internal class SeatGradeEntity(
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),

    @Column(name = "gradeName", nullable = false)
    val gradeName: String,

    @Column(name = "price", nullable = false)
    val price: Long,

    @Column(name = "performanceId", nullable = false)
    val performanceId: UUID
) : BaseTimeEntity()

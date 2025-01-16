package com.hunhui.ticketworld.infra.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "seat")
internal class SeatEntity(
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),
    @Column(name = "gradeId", nullable = false)
    val gradeId: UUID,
    @Column(name = "seatName", nullable = false)
    val seatName: String,
    @Column(name = "x", nullable = false)
    val x: Int,
    @Column(name = "y", nullable = false)
    val y: Int,
) : BaseTimeEntity()

package com.hunhui.ticketworld.infra.jpa.entity

import com.hunhui.ticketworld.domain.discount.DiscountConditionType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "discount_condition")
internal class DiscountConditionEntity(
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),
    @Column(name = "discount_id", nullable = false)
    val discountId: UUID,
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    val type: DiscountConditionType,
    @Column(name = "data", nullable = false, columnDefinition = "TEXT")
    val data: String,
) : BaseTimeEntity()

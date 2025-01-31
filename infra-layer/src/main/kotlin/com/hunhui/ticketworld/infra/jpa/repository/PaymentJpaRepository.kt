package com.hunhui.ticketworld.infra.jpa.repository

import com.hunhui.ticketworld.infra.jpa.entity.PaymentEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

internal interface PaymentJpaRepository : JpaRepository<PaymentEntity, UUID>

package com.hunhui.ticketworld.infra.jpa.repository

import com.hunhui.ticketworld.domain.payment.Payment
import com.hunhui.ticketworld.domain.payment.PaymentRepository
import com.hunhui.ticketworld.infra.jpa.entity.PaymentEntity
import com.hunhui.ticketworld.infra.jpa.entity.PaymentInfoEntity
import org.springframework.stereotype.Repository

@Repository
internal class PaymentRepositoryImpl(
    private val paymentJpaRepository: PaymentJpaRepository,
) : PaymentRepository {
    override fun save(payment: Payment) {
        paymentJpaRepository.save(payment.entity)
    }

    private val Payment.entity: PaymentEntity
        get() =
            PaymentEntity(
                id = id,
                userId = userId,
                paymentMethod = paymentMethod,
                paymentInfos =
                    paymentInfos.map {
                        PaymentInfoEntity(
                            id = it.id,
                            paymentId = id,
                            performancePriceId = it.performancePriceId,
                            reservationCount = it.reservationCount,
                            discountId = it.discountId,
                            paymentAmount = it.paymentAmount.amount,
                        )
                    },
            )
}

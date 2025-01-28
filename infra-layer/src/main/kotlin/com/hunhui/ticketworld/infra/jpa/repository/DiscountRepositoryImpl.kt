package com.hunhui.ticketworld.infra.jpa.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.domain.discount.Certificate
import com.hunhui.ticketworld.domain.discount.CertificateDiscount
import com.hunhui.ticketworld.domain.discount.Discount
import com.hunhui.ticketworld.domain.discount.DiscountApplyCountType
import com.hunhui.ticketworld.domain.discount.DiscountApplyInf
import com.hunhui.ticketworld.domain.discount.DiscountApplyMax
import com.hunhui.ticketworld.domain.discount.DiscountApplyMultiple
import com.hunhui.ticketworld.domain.discount.DiscountApplySelf
import com.hunhui.ticketworld.domain.discount.DiscountCondition
import com.hunhui.ticketworld.domain.discount.DiscountConditionData
import com.hunhui.ticketworld.domain.discount.DiscountConditionType
import com.hunhui.ticketworld.domain.discount.DiscountRate
import com.hunhui.ticketworld.domain.discount.DiscountRepository
import com.hunhui.ticketworld.domain.discount.PerformancePriceId
import com.hunhui.ticketworld.domain.discount.PerformancePriceIdDiscount
import com.hunhui.ticketworld.domain.discount.ReservationDate
import com.hunhui.ticketworld.domain.discount.ReservationDateDiscount
import com.hunhui.ticketworld.domain.discount.RoundIdDiscount
import com.hunhui.ticketworld.domain.discount.RoundIds
import com.hunhui.ticketworld.domain.discount.exception.DiscountErrorCode
import com.hunhui.ticketworld.infra.jpa.entity.DiscountConditionEntity
import com.hunhui.ticketworld.infra.jpa.entity.DiscountEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
internal class DiscountRepositoryImpl(
    private val discountJpaRepository: DiscountJpaRepository,
) : DiscountRepository {
    private val objectMapper =
        ObjectMapper().registerModule(JavaTimeModule()).registerKotlinModule()

    override fun getById(id: UUID): Discount =
        discountJpaRepository.findByIdOrNull(id)?.domain ?: throw BusinessException(DiscountErrorCode.NOT_FOUND)

    override fun findAllByIds(ids: List<UUID>): List<Discount> = discountJpaRepository.findAllById(ids).map { it.domain }

    override fun findAllByPerformanceId(performanceId: UUID): List<Discount> =
        discountJpaRepository
            .findAllByPerformanceId(performanceId)
            .map { it.domain }

    override fun save(discount: Discount) {
        discountJpaRepository.save(discount.entity)
    }

    private val DiscountEntity.domain: Discount
        get() =
            Discount(
                id = id,
                performanceId = performanceId,
                discountName = discountName,
                discountConditions = discountConditions.map { it.domain },
                applyCount =
                    when (applyCountType) {
                        DiscountApplyCountType.MAX -> DiscountApplyMax(amount = applyCountAmount!!)
                        DiscountApplyCountType.MULTIPLE -> DiscountApplyMultiple(amount = applyCountAmount!!)
                        DiscountApplyCountType.SELF -> DiscountApplySelf
                        DiscountApplyCountType.INF -> DiscountApplyInf
                    },
                discountRate = DiscountRate(discountRate),
            )

    private val DiscountConditionEntity.domain: DiscountCondition
        get() =
            when (type) {
                DiscountConditionType.RESERVATION_DATE ->
                    ReservationDateDiscount(
                        id = id,
                        type = type,
                        data = objectMapper.readValue(data, ReservationDate::class.java),
                    )
                DiscountConditionType.ROUND_ID ->
                    RoundIdDiscount(
                        id = id,
                        type = type,
                        data = objectMapper.readValue(data, RoundIds::class.java),
                    )
                DiscountConditionType.CERTIFICATE ->
                    CertificateDiscount(
                        id = id,
                        type = type,
                        data = objectMapper.readValue(data, Certificate::class.java),
                    )
                DiscountConditionType.PERFORMANCE_PRICE_ID ->
                    PerformancePriceIdDiscount(
                        id = id,
                        type = type,
                        data = objectMapper.readValue(data, PerformancePriceId::class.java),
                    )
            }

    private val Discount.entity: DiscountEntity
        get() =
            DiscountEntity(
                id = id,
                performanceId = performanceId,
                discountName = discountName,
                discountConditions =
                    discountConditions.map {
                        DiscountConditionEntity(
                            id = it.id,
                            discountId = id,
                            type = it.type,
                            data = it.data.serialize(),
                        )
                    },
                applyCountType = applyCount.type,
                applyCountAmount = applyCount.amount,
                discountRate = discountRate.rate,
            )

    private fun DiscountConditionData.serialize(): String = objectMapper.writeValueAsString(this)
}

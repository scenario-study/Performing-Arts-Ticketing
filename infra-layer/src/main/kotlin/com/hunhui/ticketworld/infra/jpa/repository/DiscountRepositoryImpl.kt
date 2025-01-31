package com.hunhui.ticketworld.infra.jpa.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.domain.discount.Discount
import com.hunhui.ticketworld.domain.discount.DiscountApplyCount
import com.hunhui.ticketworld.domain.discount.DiscountCondition
import com.hunhui.ticketworld.domain.discount.DiscountRate
import com.hunhui.ticketworld.domain.discount.DiscountRepository
import com.hunhui.ticketworld.domain.discount.exception.DiscountErrorCode
import com.hunhui.ticketworld.infra.jpa.entity.DiscountConditionEntity
import com.hunhui.ticketworld.infra.jpa.entity.DiscountEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
internal class DiscountRepositoryImpl(
    private val discountJpaRepository: DiscountJpaRepository,
    private val objectMapper: ObjectMapper,
) : DiscountRepository {
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
                applyCount = DiscountApplyCount.create(applyCountType, applyCountAmount),
                discountRate = DiscountRate(discountRate),
            )

    private val DiscountConditionEntity.domain: DiscountCondition
        get() = objectMapper.readValue(this.data, DiscountCondition::class.java)

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
                            data = it.serialize(),
                        )
                    },
                applyCountType = applyCount.type,
                applyCountAmount = applyCount.amount,
                discountRate = discountRate.rate,
            )

    private fun DiscountCondition.serialize(): String = objectMapper.writeValueAsString(this)
}

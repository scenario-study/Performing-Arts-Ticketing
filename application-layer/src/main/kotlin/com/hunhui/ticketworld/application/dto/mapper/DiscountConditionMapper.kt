package com.hunhui.ticketworld.application.dto.mapper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.hunhui.ticketworld.domain.discount.Certificate
import com.hunhui.ticketworld.domain.discount.CertificateDiscount
import com.hunhui.ticketworld.domain.discount.DiscountCondition
import com.hunhui.ticketworld.domain.discount.DiscountConditionType
import com.hunhui.ticketworld.domain.discount.PerformancePriceId
import com.hunhui.ticketworld.domain.discount.PerformancePriceIdDiscount
import com.hunhui.ticketworld.domain.discount.ReservationDate
import com.hunhui.ticketworld.domain.discount.ReservationDateDiscount
import com.hunhui.ticketworld.domain.discount.RoundIdDiscount
import com.hunhui.ticketworld.domain.discount.RoundIds
import java.util.UUID

object DiscountConditionMapper {
    private val objectMapper: ObjectMapper = ObjectMapper().registerModule(JavaTimeModule()).registerKotlinModule()

    fun toDomain(
        type: DiscountConditionType,
        data: Map<String, Any>,
    ): DiscountCondition {
        val json = objectMapper.writeValueAsString(data)
        return when (type) {
            DiscountConditionType.RESERVATION_DATE ->
                ReservationDateDiscount(
                    id = UUID.randomUUID(),
                    type = type,
                    data = objectMapper.readValue(json, ReservationDate::class.java),
                )
            DiscountConditionType.ROUND_ID ->
                RoundIdDiscount(
                    id = UUID.randomUUID(),
                    type = type,
                    data = objectMapper.readValue(json, RoundIds::class.java),
                )
            DiscountConditionType.CERTIFICATE ->
                CertificateDiscount(
                    id = UUID.randomUUID(),
                    type = type,
                    data = objectMapper.readValue(json, Certificate::class.java),
                )
            DiscountConditionType.PERFORMANCE_PRICE_ID ->
                PerformancePriceIdDiscount(
                    id = UUID.randomUUID(),
                    type = type,
                    data = objectMapper.readValue(json, PerformancePriceId::class.java),
                )
        }
    }
}

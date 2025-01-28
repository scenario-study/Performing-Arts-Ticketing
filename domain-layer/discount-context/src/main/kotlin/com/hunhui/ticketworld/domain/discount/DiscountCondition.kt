package com.hunhui.ticketworld.domain.discount

import java.time.LocalDateTime
import java.util.UUID

interface DiscountCondition {
    val id: UUID
    val type: DiscountConditionType
    val data: DiscountConditionData

    fun canApply(
        roundId: UUID,
        priceId: UUID,
    ): Boolean
}

interface DiscountConditionData

data class ReservationDate(
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
) : DiscountConditionData

data class RoundIds(
    val roundIds: List<UUID>,
) : DiscountConditionData

data class Certificate(
    val message: String,
) : DiscountConditionData

data class PerformancePriceId(
    val performancePriceId: UUID,
) : DiscountConditionData

data class ReservationDateDiscount(
    override val id: UUID,
    override val type: DiscountConditionType = DiscountConditionType.RESERVATION_DATE,
    override val data: ReservationDate,
) : DiscountCondition {
    override fun canApply(
        roundId: UUID,
        priceId: UUID,
    ): Boolean {
        val now = LocalDateTime.now()
        return now.isAfter(data.startDate) && now.isBefore(data.endDate)
    }
}

data class RoundIdDiscount(
    override val id: UUID,
    override val type: DiscountConditionType = DiscountConditionType.ROUND_ID,
    override val data: RoundIds,
) : DiscountCondition {
    override fun canApply(
        roundId: UUID,
        priceId: UUID,
    ): Boolean = data.roundIds.contains(roundId)
}

data class CertificateDiscount(
    override val id: UUID,
    override val type: DiscountConditionType = DiscountConditionType.CERTIFICATE,
    override val data: Certificate,
) : DiscountCondition {
    override fun canApply(
        roundId: UUID,
        priceId: UUID,
    ): Boolean = true
}

data class PerformancePriceIdDiscount(
    override val id: UUID,
    override val type: DiscountConditionType = DiscountConditionType.PERFORMANCE_PRICE_ID,
    override val data: PerformancePriceId,
) : DiscountCondition {
    override fun canApply(
        roundId: UUID,
        priceId: UUID,
    ): Boolean = priceId == data.performancePriceId
}

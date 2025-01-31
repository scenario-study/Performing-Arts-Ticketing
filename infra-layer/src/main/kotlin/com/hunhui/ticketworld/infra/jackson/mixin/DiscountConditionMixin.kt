package com.hunhui.ticketworld.infra.jackson.mixin

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.hunhui.ticketworld.domain.discount.DiscountCondition

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
)
@JsonSubTypes(
    JsonSubTypes.Type(DiscountCondition.ReservationDate::class, name = "RESERVATION_DATE"),
    JsonSubTypes.Type(DiscountCondition.RoundIds::class, name = "ROUND_ID"),
    JsonSubTypes.Type(DiscountCondition.Certificate::class, name = "CERTIFICATE"),
    JsonSubTypes.Type(DiscountCondition.PerformancePriceId::class, name = "PERFORMANCE_PRICE_ID"),
)
interface DiscountConditionMixin

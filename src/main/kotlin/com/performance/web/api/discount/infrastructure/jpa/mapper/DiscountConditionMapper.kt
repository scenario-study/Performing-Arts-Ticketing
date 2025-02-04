package com.performance.web.api.discount.infrastructure.jpa.mapper

import com.performance.web.api.discount.domain.DateRangeCondition
import com.performance.web.api.discount.domain.DiscountCondition
import com.performance.web.api.discount.domain.TimeRangeCondition
import com.performance.web.api.discount.infrastructure.jpa.DateRangeConditionEntity
import com.performance.web.api.discount.infrastructure.jpa.DiscountConditionEntity
import com.performance.web.api.discount.infrastructure.jpa.TimeRangeConditionEntity

class DiscountConditionMapper {

    companion object {

        fun fromDomainToEntity(discountCondition: DiscountCondition): DiscountConditionEntity {
            return when (discountCondition) {
                is TimeRangeCondition -> fromTimeRangeToEntity(discountCondition)
                is DateRangeCondition -> fromDateRangeToEntity(discountCondition)
                else -> throw IllegalArgumentException("Discount condition type ${discountCondition.javaClass} not supported")
            }
        }


        private fun fromTimeRangeToEntity(timeRangeCondition: TimeRangeCondition): TimeRangeConditionEntity {
            return TimeRangeConditionEntity(
                id = timeRangeCondition.getId(),
                startTime = timeRangeCondition.getStartTime(),
                endTime = timeRangeCondition.getEndTime(),
            )
        }

        private fun fromDateRangeToEntity(dateRangeCondition: DateRangeCondition): DateRangeConditionEntity {
            return DateRangeConditionEntity(
                id = dateRangeCondition.getId(),
                startDate = dateRangeCondition.getStartDate(),
                endDate = dateRangeCondition.getEndDate(),
            )
        }
    }
}

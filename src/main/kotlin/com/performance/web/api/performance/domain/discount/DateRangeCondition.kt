package com.performance.web.api.performance.domain.discount

import java.time.LocalDate
import java.time.LocalDateTime

class DateRangeCondition(
    startDateTime: LocalDateTime,
    endDateTime: LocalDateTime,
) : DiscountCondition {

    private val _startDate: LocalDate
    private val _endDate: LocalDate

    init {
        this._startDate = startDateTime.toLocalDate()
        this._endDate = endDateTime.toLocalDate()
    }

    override fun isSatisfiedBy(discountFactor: DiscountFactor): Boolean {
        val reserveDate = discountFactor.reserveDateTime.toLocalDate()
        return !reserveDate.isBefore(_startDate) && !reserveDate.isAfter(_endDate)
    }
}

package com.performance.web.api.discount.domain

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

class PeriodCondition(
    dayOfWeek: DayOfWeek,
    startDateTime: LocalDateTime,
    endDateTime: LocalDateTime,
) : DiscountCondition {

    private val _dayOfWeek: DayOfWeek = dayOfWeek
    private val _startTime: LocalTime
    private val _endTime: LocalTime

    init {
        this._startTime = startDateTime.toLocalTime()
        this._endTime = endDateTime.toLocalTime()
    }

    override fun isSatisfiedBy(discountFactor: DiscountFactor): Boolean {
        val reserveDateTime = discountFactor.reserveDateTime

        val isDayOfWeekMatched = reserveDateTime.dayOfWeek == _dayOfWeek

        val reserveTime = reserveDateTime.toLocalTime()
        val isTimeInRange = !reserveTime.isBefore(_startTime) && !reserveTime.isAfter(_endTime)

        return isDayOfWeekMatched && isTimeInRange
    }
}

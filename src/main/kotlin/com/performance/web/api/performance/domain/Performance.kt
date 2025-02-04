package com.performance.web.api.performance.domain

import com.performance.web.api.common.domain.BusinessException
import java.time.LocalDate

class Performance(
    id: Long = 0L,
    name: String,
    runTimeInMinutes: Long,
    startDate: LocalDate,
    endDate: LocalDate,
    description: String,
    seatClasses: List<PerformanceSeatClass> = mutableListOf()
) {

    private val _id: Long = id
    private val _name: String = name
    private val _runTime: Long = runTimeInMinutes
    private val _startDate: LocalDate = startDate
    private val _endDate: LocalDate = endDate
    private val _description: String = description
    private val _seatClasses: List<PerformanceSeatClass> = seatClasses

    fun getId(): Long = _id
    fun getName(): String = _name
    fun getRunTime(): Long = _runTime
    fun getStartDate(): LocalDate = _startDate
    fun getEndDate(): LocalDate = _endDate
    fun getDescription(): String = _description
    fun getSeatClasses(): List<PerformanceSeatClass> = _seatClasses


    fun findSeatClassById(performanceSeatClassId: Long): PerformanceSeatClass =
        _seatClasses.find { it.getId() == performanceSeatClassId }
            ?: throw BusinessException("$performanceSeatClassId 에 해당하는 SeatClass가 없습니다. ")

}

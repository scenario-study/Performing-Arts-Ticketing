package com.performance.web.api.reservation.domain

class Performance(
    name: String,
    runTimeInMinutes: Long
) {
    private val _name: String = name
    private val _runTime: Long = runTimeInMinutes

    fun getName(): String = _name
    fun getRunTime(): Long = _runTime
}

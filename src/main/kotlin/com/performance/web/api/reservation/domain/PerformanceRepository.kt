package com.performance.web.api.reservation.domain

import java.util.*

interface PerformanceRepository {

    fun findById(id: Long): Optional<Performance>

    fun findByPage(pageNum: Int): List<Performance>

    fun findAll(): List<Performance>
}

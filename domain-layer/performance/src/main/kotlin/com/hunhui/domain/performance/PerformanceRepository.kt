package com.hunhui.domain.performance

import java.util.UUID

interface PerformanceRepository {
    fun findById(id: UUID): Performance?
    fun findAll(page: Int, size: Int): List<Performance>
    fun save(performance: Performance)
}

package com.performance.web.api.mock

import com.performance.web.api.performance.domain.Performance
import com.performance.web.api.performance.domain.PerformanceRepository
import java.util.*

class FakePerformanceRepository : PerformanceRepository {

    override fun findById(id: Long): Optional<Performance> {
        TODO("Not yet implemented")
    }

    override fun findByPage(pageNum: Int): List<Performance> {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Performance> {
        TODO("Not yet implemented")
    }
}

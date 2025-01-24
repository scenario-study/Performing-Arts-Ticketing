package com.performance.web.api.reservation.infrastructure.memory

import com.performance.web.api.common.domain.Money
import com.performance.web.api.reservation.domain.Performance
import com.performance.web.api.reservation.domain.PerformanceRepository
import com.performance.web.api.reservation.domain.SeatClass
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Component
class MemoryBasedPerformanceRepositoryImpl : PerformanceRepository {

    private final val store = ConcurrentHashMap<Long, Performance>(
        mapOf(
            1L to Performance(
                id = 1L,
                name = "공연1",
                runTimeInMinutes = 100,
                startDate = LocalDate.of(2025, 1, 1),
                endDate = LocalDate.of(2025, 2, 1),
                description = "1달동안 하는 공연",
                seatClasses = listOf(
                    SeatClass(
                        price = Money.of(10000),
                        classType = "VIP",
                    ),
                    SeatClass(
                        price = Money.of(8000),
                        classType = "R",
                    ),
                ),
            ),
            2L to Performance(
                id = 2L,
                name = "공연2",
                runTimeInMinutes = 200,
                startDate = LocalDate.of(2025, 1, 1),
                endDate = LocalDate.of(2025, 3, 1),
                description = "2달동안 하는 공연",
                seatClasses = listOf(
                    SeatClass(
                        price = Money.of(20000),
                        classType = "VIP",
                    ),
                    SeatClass(
                        price = Money.of(10000),
                        classType = "R",
                    ),
                ),
            ),
        ),
    )


    override fun findById(id: Long): Optional<Performance> {
        return Optional.ofNullable(store[id])
    }

    override fun findByPage(pageNum: Int): List<Performance> {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Performance> {
        return store.values.toList()
    }
}

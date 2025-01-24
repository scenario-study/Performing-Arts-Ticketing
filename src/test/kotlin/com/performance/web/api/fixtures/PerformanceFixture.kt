package com.performance.web.api.fixtures

import com.performance.web.api.reservation.domain.Performance
import com.performance.web.api.reservation.domain.SeatClass
import java.time.LocalDate

class PerformanceFixture {

    companion object {
        fun create(
            id: Long = 0L,
            name: String = "공연",
            runTimeMinutes: Long = 100,
            startDate: LocalDate = LocalDate.now(),
            endDate: LocalDate = LocalDate.now().plusDays(1),
            description: String = "설명설명",
            seatClasses: List<SeatClass> = emptyList()
        ): Performance =
            Performance(
                id = id,
                name = name,
                runTimeInMinutes = runTimeMinutes,
                startDate = startDate,
                endDate = endDate,
                description = description,
                seatClasses = seatClasses,
            )
    }
}

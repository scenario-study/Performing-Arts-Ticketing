package com.performance.web.api.fixtures

import com.performance.web.api.reservation.domain.Performance
import com.performance.web.api.reservation.domain.Seat
import com.performance.web.api.reservation.domain.Session
import java.time.LocalDateTime

class SessionFixture {

    companion object {

        fun create(
            id: Long = 0L,
            performance: Performance = PerformanceFixture.create(),
            startDate: LocalDateTime = LocalDateTime.now(),
            seats: List<Seat> = listOf(SeatFixture.create(), SeatFixture.create()),
        ): Session =
            Session(
                id = id,
                performance = performance,
                startDateTime = startDate,
                seats = seats,
            )
    }
}

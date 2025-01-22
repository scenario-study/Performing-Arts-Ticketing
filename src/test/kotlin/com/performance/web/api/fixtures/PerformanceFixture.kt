package com.performance.web.api.fixtures

import com.performance.web.api.reservation.domain.Performance

class PerformanceFixture {

    companion object {
        fun create(
            name: String = "공연",
            runTimeMinutes: Long = 100,
        ): Performance =
            Performance(
                name = name,
                runTimeInMinutes = runTimeMinutes,
            )
    }
}

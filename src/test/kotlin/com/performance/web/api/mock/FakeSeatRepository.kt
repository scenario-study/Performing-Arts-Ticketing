package com.performance.web.api.mock

import com.performance.web.api.seat.domain.Seat
import com.performance.web.api.seat.domain.SeatRepository
import java.util.*

class FakeSeatRepository: SeatRepository {

    override fun findById(id: Long): Optional<Seat> {
        TODO("Not yet implemented")
    }

    override fun save(seat: Seat): Seat {
        TODO("Not yet implemented")
    }
}

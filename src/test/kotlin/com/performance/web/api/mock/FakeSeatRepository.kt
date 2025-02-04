package com.performance.web.api.mock

import com.performance.web.api.seat.domain.Seat
import com.performance.web.api.seat.domain.SeatRepository
import java.util.*

class FakeSeatRepository: SeatRepository {

    private var autoIncrementId = 1L
    private val store = mutableMapOf<Long, Seat>()

    override fun findById(id: Long): Optional<Seat> {
        return Optional.ofNullable(store[id])
    }

    override fun save(seat: Seat): Seat {
        if(seat.getId() == 0L){

            val newSeat = Seat(
                id = autoIncrementId++,
                seatClass = seat.getSeatClass(),
                seatStatus = seat.getSeatStatus(),
                seatPosition = seat.getSeatPosition()
            )
            store.put(newSeat.getId(), newSeat)
            return newSeat
        }
        val newSeat = Seat(
            id = seat.getId(),
            seatClass = seat.getSeatClass(),
            seatStatus = seat.getSeatStatus(),
            seatPosition = seat.getSeatPosition()
        )

        store.put(newSeat.getId(), newSeat)
        return newSeat
    }
}

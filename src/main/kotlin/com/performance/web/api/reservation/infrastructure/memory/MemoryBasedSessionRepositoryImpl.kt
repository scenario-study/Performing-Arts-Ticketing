package com.performance.web.api.reservation.infrastructure.memory

import com.performance.web.api.common.domain.Money
import com.performance.web.api.reservation.domain.*
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Component
class MemoryBasedSessionRepositoryImpl : SessionRepository {

    private val sessionKey = AtomicLong(1L)
    private val seatKey = AtomicLong(4L)

    private final val sessionStore =
        ConcurrentHashMap<Long, Session>(
            mapOf(
                1L to
                    Session(
                        id = 1L,
                        performance = Performance("공연", runTimeInMinutes = 180L),
                        startDateTime = LocalDateTime.of(2025, 1, 1, 15, 10),
                    ),
            ),
        )

    private final val seatStores =
        ConcurrentHashMap<Long, List<Seat>>(
            mapOf(
                1L to
                    listOf(
                        Seat(
                            id = 1L,
                            seatClass = SeatClass(Money.of(20000), "VIP"),
                            seatStatus = SeatStatus.UN_RESERVED,
                            seatPosition = SeatPosition(1, 1),
                        ),
                        Seat(
                            id = 2L,
                            seatClass = SeatClass(Money.of(10000), "R"),
                            seatStatus = SeatStatus.UN_RESERVED,
                            seatPosition = SeatPosition(1, 2),
                        ),
                    ),
                2L to
                    listOf(
                        Seat(
                            id = 3L,
                            seatClass = SeatClass(Money.of(10000), "R"),
                            seatStatus = SeatStatus.UN_RESERVED,
                            seatPosition = SeatPosition(1, 3),
                        ),
                        Seat(
                            id = 4L,
                            seatClass = SeatClass(Money.of(15000), "S"),
                            seatStatus = SeatStatus.UN_RESERVED,
                            seatPosition = SeatPosition(1, 4),
                        ),
                    ),
            ),
        )

    override fun findById(id: Long): Optional<Session> {
        return Optional.ofNullable(sessionStore[id])
    }

    override fun findByIdWithSeatAnsClassAndPerformance(id: Long): Optional<Session> {
        val session = sessionStore[id] ?: return Optional.empty()

        return Optional.ofNullable(
            Session(
                id = session.getId(),
                performance = session.getPerformance(),
                seats = seatStores.getOrDefault(id, mutableListOf()),
                startDateTime = session.getStartDateTime(),
            ),
        )
    }

    override fun save(session: Session) {
        val key = if (session.getId() == 0L) sessionKey.incrementAndGet() else session.getId()

        sessionStore[key] =
            Session(
                id = key,
                performance = session.getPerformance(),
                seats =
                    session.getSeats().map { seat ->
                        Seat(
                            id = if (seat.getId() == 0L) seatKey.incrementAndGet() else seat.getId(),
                            seatClass = seat.getSeatClass(),
                            seatStatus = seat.getSeatStatus(),
                            seatPosition = seat.getSeatPosition(),
                        )
                    },
                startDateTime = session.getStartDateTime(),
            )
    }
}

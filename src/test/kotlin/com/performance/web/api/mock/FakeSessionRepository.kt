package com.performance.web.api.mock

import com.performance.web.api.reservation.domain.Session
import com.performance.web.api.reservation.domain.SessionRepository
import java.util.*

class FakeSessionRepository : SessionRepository {

    private var autoIncrementId = 1L;
    private val store = mutableMapOf<Long, Session>()

    override fun findById(id: Long): Optional<Session> {
        return Optional.ofNullable(store[id])
    }

    override fun findByIdWithSeatAnsClassAndPerformance(id: Long): Optional<Session> {
        return Optional.ofNullable(store[id])
    }

    override fun save(session: Session) {
        if(session.getId() == 0L){
            val newKey = autoIncrementId++;

            val newSesson = Session(
                id = newKey,
                performance = session.getPerformance(),
                startDateTime = session.getStartDateTime(),
                seats = session.getSeats(),
            )

            store[newKey] = newSesson
        }else{
            store[session.getId()] = session
        }
    }
}

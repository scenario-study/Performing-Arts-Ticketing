package com.performance.web.api.reservation.domain

import java.util.Optional

interface SessionRepository {

    fun findById(id: Long): Optional<Session>

    fun findByIdWithSeatAnsClassAndPerformance(id: Long): Optional<Session>

    fun save(session: Session)
}

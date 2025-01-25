package com.performance.web.api.reservation.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface SessionJpaRepository: JpaRepository<SessionEntity, Long> {

    @Query("SELECT s FROM SessionEntity s " +
        "JOIN FETCH s.performance p " +
        "JOIN FETCH s.seats se " +
        "JOIN FETCH se.seatClass sc " +
        "WHERE s.id = :sessionId")
    fun findSessionWithPerformanceAndSeats(@Param("sessionId") sessionId: Long): Optional<SessionEntity>
}

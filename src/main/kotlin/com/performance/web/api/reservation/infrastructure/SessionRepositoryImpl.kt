package com.performance.web.api.reservation.infrastructure

import com.performance.web.api.common.domain.BusinessException
import com.performance.web.api.reservation.domain.Session
import com.performance.web.api.reservation.domain.SessionRepository
import com.performance.web.api.reservation.infrastructure.jpa.SeatClassJpaRepository
import com.performance.web.api.reservation.infrastructure.jpa.SessionJpaRepository
import org.springframework.stereotype.Component
import java.util.*


@Component
class SessionRepositoryImpl(
    private val sessionJpaRepository: SessionJpaRepository,
    private val seatClassJpaRepository: SeatClassJpaRepository
) : SessionRepository {

    override fun findById(id: Long): Optional<Session> {
        return sessionJpaRepository.findById(id).map { it.toDomain() }
    }

    /*
    *  TODO 1. : 쿼리 최적화
    *  TODO 2. : 쿼리 맵핑
    * */

    override fun findByIdWithSeatAnsClassAndPerformance(id: Long): Optional<Session> {
        val optionalSession = sessionJpaRepository.findSessionWithPerformanceAndSeats(id)
        if (optionalSession.isEmpty) return Optional.empty()

        val session = optionalSession.get()

        val seatClassIds = session.performance.seatClasses.map { it.id }
        val seatClassWithDiscounts = seatClassJpaRepository.findSeatClassWithDiscounts(seatClassIds)

        session.seats.forEach { seat ->
            val seatClass =
                seatClassWithDiscounts.find { it.id == seat.seatClass.id } ?: throw BusinessException("좌석 클래스 정보 불일치")
            seat.seatClass = seatClass
        }

        return Optional.of(session.toDomain())
    }

    override fun save(session: Session) {
        TODO("Not yet implemented")
    }
}

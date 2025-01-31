package com.performance.web.api.reservation.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SeatClassJpaRepository : JpaRepository<SeatClassEntity, Long> {


    @Query("SELECT s FROM SeatClassEntity s " +
        "JOIN FETCH s.discountPolicies dp " +
        "JOIN FETCH dp.conditions dc " +
        "WHERE s.id in :seatClassIds "
    )
    fun findSeatClassWithDiscounts(@Param("seatClassIds") seatClassIds: List<Long> ) : List<SeatClassEntity>
}

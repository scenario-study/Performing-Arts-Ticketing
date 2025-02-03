package com.performance.web.api.performance.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface PerformanceJpaRepository : JpaRepository<PerformanceEntity, Long> {

    @Query("SELECT e FROM PerformanceEntity e " +
        "JOIN FETCH e.seatClasses sc " +
        "JOIN FETCH sc.discountPolicies dp " +
        "WHERE e.id = :performanceId")
    fun findPerformanceEntityWithSeatClassAndDiscountPolicies(@Param("performanceId") performanceId:Long): Optional<PerformanceEntity>

    @Query("SELECT e FROM PerformanceEntity e " +
        "JOIN FETCH e.seatClasses sc " +
        "WHERE e.id = :performanceId")
    fun findPerformanceEntityByWithSeatClasses(@Param("performanceId") performanceId:Long) : Optional<PerformanceEntity>
}

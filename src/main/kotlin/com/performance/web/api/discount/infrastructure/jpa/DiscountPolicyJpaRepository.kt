package com.performance.web.api.discount.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DiscountPolicyJpaRepository : JpaRepository<DiscountPolicyEntity, Long> {

    @Query("""
        SELECT dp
        FROM DiscountPolicyEntity dp
        JOIN FETCH dp.conditions dc
        WHERE dp.seatClass.id IN :seatClassIds
    """)
    fun findAllWithConditionsBySeatClassIds(@Param("seatClassIds") seatClassIds: List<Long>): List<DiscountPolicyEntity>

}

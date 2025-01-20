package com.cd18.infra.persistence.repository.jpa

import com.cd18.infra.persistence.model.HistoryPerformanceDiscount
import org.springframework.data.jpa.repository.JpaRepository

interface HistoryPerformanceDiscountJpaRepository : JpaRepository<HistoryPerformanceDiscount, Long>

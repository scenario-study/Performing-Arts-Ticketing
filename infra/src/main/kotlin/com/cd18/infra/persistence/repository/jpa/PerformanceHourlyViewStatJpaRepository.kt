package com.cd18.infra.persistence.repository.jpa

import com.cd18.infra.persistence.model.PerformanceHourlyViewStat
import org.springframework.data.jpa.repository.JpaRepository

interface PerformanceHourlyViewStatJpaRepository : JpaRepository<PerformanceHourlyViewStat, Long>

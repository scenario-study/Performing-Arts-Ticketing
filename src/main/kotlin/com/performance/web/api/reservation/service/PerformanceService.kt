package com.performance.web.api.reservation.service

import com.performance.web.api.common.domain.ResourceNotFoundException
import com.performance.web.api.reservation.domain.Performance
import com.performance.web.api.reservation.domain.PerformanceRepository
import org.springframework.stereotype.Service


@Service
class PerformanceService(
    private val performanceRepository: PerformanceRepository
) {


    fun findById(id: Long): Performance {
        return performanceRepository.findById(id)
            .orElseThrow { throw ResourceNotFoundException("$id 에 해당하는 Performance를 찾을 수 없습니다.") }
    }

    fun findAll(): List<Performance> {
        return performanceRepository.findAll()
    }


}

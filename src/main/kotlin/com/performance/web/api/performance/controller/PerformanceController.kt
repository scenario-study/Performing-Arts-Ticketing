package com.performance.web.api.performance.controller

import com.performance.web.api.performance.controller.dto.PerformanceDetailApiResponse
import com.performance.web.api.performance.controller.dto.PerformanceDiscountApiResponse
import com.performance.web.api.performance.controller.dto.PerformanceListApiResponse
import com.performance.web.api.performance.service.PerformanceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/performances")
class PerformanceController(
    private val performanceService: PerformanceService,
) {

    @GetMapping("/{performanceId}")
    fun getPerformance(@PathVariable performanceId: Long): ResponseEntity<PerformanceDetailApiResponse> {
        val result = performanceService.findById(performanceId)
        return ResponseEntity.ok()
            .body(PerformanceDetailApiResponse.from(result))
    }

    /*
    *  공연 정보 리스트 API
    *  필요 정보 : 공연 정보(공연 이름, 가격 정보, 공연 기간 )
    *  TODO paging 기능 및 스펙 정하기
    */

    @GetMapping("/list")
    fun getPerformanceByPaging(): ResponseEntity<List<PerformanceListApiResponse>> {
        val result = performanceService.findAll()
        return ResponseEntity.ok()
            .body(result.map { PerformanceListApiResponse.from(it) })
    }


    /*
    *  공연 좌석 할인 정보 API
    */
    @GetMapping("/{performanceId}/discount")
    fun getDiscount(@PathVariable performanceId: Long): ResponseEntity<List<PerformanceDiscountApiResponse>> {
        val result = performanceService.findSeatClassByIdWithDiscounts(performanceId);
        return ResponseEntity.ok()
            .body(result.map { PerformanceDiscountApiResponse.from(it) })
    }

}

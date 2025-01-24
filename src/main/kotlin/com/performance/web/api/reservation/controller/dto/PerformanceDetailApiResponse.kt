package com.performance.web.api.reservation.controller.dto

import com.performance.web.api.reservation.domain.Performance
import com.performance.web.api.reservation.domain.SeatClass
import java.time.LocalDate

class PerformanceDetailApiResponse(

    val id: Long,
    val name: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val description: String,
    val runTime: Long,
    val seatClassInfos: List<SeatClassApiResponse>
) {

    data class SeatClassApiResponse(
        val classType: String,
        val price: Long
    ) {
        companion object {
            fun from(seatClass: SeatClass): SeatClassApiResponse {
                return SeatClassApiResponse(
                    classType = seatClass.getClassType(),
                    price = seatClass.getPrice().longValue(),
                )
            }
        }
    }

    companion object {
        fun from(performance: Performance): PerformanceDetailApiResponse {
            return PerformanceDetailApiResponse(
                id = performance.getId(),
                name = performance.getName(),
                startDate = performance.getStartDate(),
                endDate = performance.getEndDate(),
                description = performance.getDescription(),
                runTime = performance.getRunTime(),
                seatClassInfos = performance.getSeatClasses().map { SeatClassApiResponse.from(it) },
            )
        }
    }
}

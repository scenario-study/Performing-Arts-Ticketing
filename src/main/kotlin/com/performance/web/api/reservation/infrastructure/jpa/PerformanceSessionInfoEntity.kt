package com.performance.web.api.reservation.infrastructure.jpa

import com.performance.web.api.reservation.domain.PerformanceSessionInfo
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDate
import java.time.LocalTime

@Embeddable
class PerformanceSessionInfoEntity(

    @Column(nullable = false)
    var performanceName: String,

    @Column(nullable = false)
    var sessionStartDate: LocalDate,

    @Column(nullable = false)
    var sessionStartTime: LocalTime,

    @Column(nullable = false)
    var sessionEndTime: LocalTime,
) {


    fun toDomain(): PerformanceSessionInfo {
        return PerformanceSessionInfo(
            performanceName = performanceName,
            sessionStartDate = sessionStartDate,
            sessionStartTime = sessionStartTime,
            sessionEndTime = sessionEndTime,
        )
    }


    companion object {
        fun fromDomain(performanceSessionInfo: PerformanceSessionInfo): PerformanceSessionInfoEntity {
            return PerformanceSessionInfoEntity(
                performanceName = performanceSessionInfo.performanceName,
                sessionStartDate = performanceSessionInfo.sessionStartDate,
                sessionStartTime = performanceSessionInfo.sessionStartTime,
                sessionEndTime = performanceSessionInfo.sessionEndTime,
            )
        }
    }
}

package com.hunhui.ticketworld.application.dto.request

import com.hunhui.ticketworld.domain.performance.PerformanceGenre
import com.hunhui.ticketworld.domain.performance.PerformanceRound
import com.hunhui.ticketworld.domain.performance.PerformanceStatus
import com.hunhui.ticketworld.domain.performance.SeatGrade
import java.util.UUID

data class PerformanceCreateRequest(
    val id: UUID,
    val title: String,
    val genre: PerformanceGenre,
    val imageUrl: String,
    val location: String,
    val description: String,
    val seatGrades: List<SeatGrade>,
    val status: PerformanceStatus,
    val rounds: List<PerformanceRound>
)

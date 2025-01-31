package com.hunhui.ticketworld.domain.performance

import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.common.vo.Money
import com.hunhui.ticketworld.domain.performance.exception.PerformanceErrorCode.INVALID_PRICE_ID
import com.hunhui.ticketworld.domain.performance.exception.PerformanceErrorCode.PERFORMANCE_PRICE_IS_EMPTY
import com.hunhui.ticketworld.domain.performance.exception.PerformanceErrorCode.ROUND_IS_EMPTY
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

class Performance(
    val id: UUID,
    val title: String,
    val genre: PerformanceGenre,
    val imageUrl: String,
    val location: String,
    val description: String,
    val reservationCount: Int,
    val performancePrices: List<PerformancePrice>,
    val rounds: List<PerformanceRound>,
) {
    init {
        if (performancePrices.isEmpty()) throw BusinessException(PERFORMANCE_PRICE_IS_EMPTY)
        if (rounds.isEmpty()) throw BusinessException(ROUND_IS_EMPTY)
    }

    companion object {
        fun create(
            title: String,
            genre: PerformanceGenre,
            imageUrl: String,
            location: String,
            description: String,
            reservationCount: Int,
            performancePrices: List<PerformancePrice>,
            rounds: List<PerformanceRound>,
        ): Performance =
            Performance(
                id = UUID.randomUUID(),
                title = title,
                genre = genre,
                imageUrl = imageUrl,
                location = location,
                description = description,
                reservationCount = reservationCount,
                performancePrices = performancePrices,
                rounds = rounds,
            )
    }

    val startDate: LocalDate
        get() = rounds.minOf { it.roundStartTime.toLocalDate() }
    val finishDate: LocalDate
        get() = rounds.maxOf { it.roundStartTime.toLocalDate() }
    val availableRounds: List<PerformanceRound>
        get() = rounds.filter { it.isReservationAvailable }
    val minimumReservationStartTime: LocalDateTime
        get() = rounds.minOf { it.reservationStartTime }
    val minimumPrice: Money
        get() = performancePrices.minOf { it.price }

    fun getPriceById(priceId: UUID): Money = performancePrices.find { it.id == priceId }?.price ?: throw BusinessException(INVALID_PRICE_ID)
}

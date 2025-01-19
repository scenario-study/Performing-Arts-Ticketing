package com.hunhui.ticketworld.domain.performance

import java.time.LocalDateTime

object PerformanceFixtureFactory {
    fun createValidPerformance(
        title: String = "테스트 공연",
        genre: PerformanceGenre = PerformanceGenre.CONCERT,
        imageUrl: String = "test_image.png",
        location: String = "테스트 장소",
        description: String = "테스트 공연 설명",
        performancePrices: List<PerformancePrice> = listOf(createValidPerformancePrice(), createValidPerformancePrice("R석", 80000)),
        rounds: List<PerformanceRound> =
            listOf(
                createValidPerformanceRound(),
                createValidPerformanceRound(
                    LocalDateTime.now().plusDays(2),
                    LocalDateTime.now().minusDays(1),
                    LocalDateTime.now().plusHours(1),
                ),
            ),
    ): Performance =
        Performance.create(
            title = title,
            genre = genre,
            imageUrl = imageUrl,
            location = location,
            description = description,
            performancePrices = performancePrices,
            rounds = rounds,
        )

    private fun createValidPerformancePrice(
        priceName: String = "VIP",
        price: Long = 100000,
    ): PerformancePrice = PerformancePrice.create(priceName, price)

    private fun createValidPerformanceRound(
        performanceDateTime: LocalDateTime = LocalDateTime.now().plusDays(3),
        reservationStartDateTime: LocalDateTime = LocalDateTime.now(),
        reservationFinishDateTime: LocalDateTime = LocalDateTime.now().plusDays(1),
    ): PerformanceRound =
        PerformanceRound.create(
            performanceDateTime,
            reservationStartDateTime,
            reservationFinishDateTime,
        )
}

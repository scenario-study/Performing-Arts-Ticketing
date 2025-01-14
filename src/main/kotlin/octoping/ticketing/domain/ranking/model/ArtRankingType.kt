package octoping.ticketing.domain.ranking.model

import java.time.DayOfWeek
import java.time.LocalDateTime

enum class ArtRankingType(
    private val checkIsSatisfied: (LocalDateTime) -> Boolean,
) {
    // 초는 혹시 오차가 생길 수 있어 지정하지 않음
    HOURLY({ it.minute == 0 }),
    DAILY({ it.hour == 0 && it.minute == 0 }),
    WEEKLY({ it.dayOfWeek == DayOfWeek.MONDAY && it.hour == 0 && it.minute == 0 }),
    ;

    fun isSatisfied(date: LocalDateTime): Boolean {
        return this.checkIsSatisfied(date)
    }
}
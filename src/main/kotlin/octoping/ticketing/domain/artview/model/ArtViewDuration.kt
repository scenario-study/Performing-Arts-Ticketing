package octoping.ticketing.domain.artview.model

import java.time.LocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.toJavaDuration

enum class ArtViewDuration(
    private val duration: Duration,
) {
    DAILY(24.hours),
    WEEKLY(7.days),
    MONTHLY(31.days),
    ;

    fun getStartDate(datetime: LocalDateTime): LocalDateTime {
        return datetime.minus(this.duration.toJavaDuration())
    }
}

package octoping.ticketing.domain.artview.model

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

internal class ArtViewDurationTest : AnnotationSpec() {
    @Test
    fun `getStartDate는 DAILY일 때 24시간 전 시각을 반환한다`() {
        // given
        val now = LocalDateTime.of(2025, 12, 31, 8, 0, 0)

        // when
        val startDate = ArtViewDuration.DAILY.getStartDate(now)

        // then
        startDate shouldBe LocalDateTime.of(2025, 12, 30, 8, 0, 0)
    }

    @Test
    fun `getStartDate는 WEEKLY일 때 7일 전 시각을 반환한다`() {
        // given
        val now = LocalDateTime.of(2025, 12, 31, 8, 0, 0)

        // when
        val startDate = ArtViewDuration.WEEKLY.getStartDate(now)

        // then
        startDate shouldBe LocalDateTime.of(2025, 12, 24, 8, 0, 0)
    }

    @Test
    fun `getStartDate는 MONTHLY일 때 31일 전 시각을 반환한다`() {
        // given
        val now = LocalDateTime.of(2025, 12, 31, 8, 0, 0)

        // when
        val startDate = ArtViewDuration.MONTHLY.getStartDate(now)

        // then
        startDate shouldBe LocalDateTime.of(2025, 11, 30, 8, 0, 0)
    }
}
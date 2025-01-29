package octoping.ticketing.domain.ranking.model

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

internal class ArtRankingTypeTest : AnnotationSpec() {
    @Test
    fun `Hourly는 매시 0분 0초에 isSatisfied를 만족한다`() {
        val now = LocalDateTime.of(2025, 1, 1, 0, 0, 0)

        ArtRankingType.HOURLY.isSatisfied(now) shouldBe true
    }

    @Test
    fun `Hourly는 매 시 0분 0초가 아니면 isSatisfied를 만족하지 않는다`() {
        val now = LocalDateTime.of(2025, 1, 1, 0, 1, 0)

        ArtRankingType.HOURLY.isSatisfied(now) shouldBe false
    }

    @Test
    fun `DAILY는 매일 0시 0분 0초에 isSatisfied를 만족한다`() {
        val now = LocalDateTime.of(2025, 1, 1, 0, 0, 0)

        ArtRankingType.DAILY.isSatisfied(now) shouldBe true
    }

    @Test
    fun `DAILY는 0시 0분 0초가 아니면 isSatisfied를 만족하지 않는다`() {
        val now = LocalDateTime.of(2025, 1, 1, 5, 0, 0)

        ArtRankingType.DAILY.isSatisfied(now) shouldBe false
    }

    @Test
    fun `Weekly는 월요일 0시 0분 0초에 isSatisfied를 만족한다`() {
        val now = LocalDateTime.of(2025, 1, 6, 0, 0, 0)

        ArtRankingType.WEEKLY.isSatisfied(now) shouldBe true
    }

    @Test
    fun `Weekly는 월요일 0시 0분 0초가 아니면 isSatisfied를 만족하지 않는다`() {
        val now = LocalDateTime.of(2025, 1, 7, 0, 0, 0)

        ArtRankingType.WEEKLY.isSatisfied(now) shouldBe false
    }
}
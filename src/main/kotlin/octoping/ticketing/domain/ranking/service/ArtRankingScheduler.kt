package octoping.ticketing.domain.ranking.service

import octoping.ticketing.domain.ranking.model.ArtRankingType
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ArtRankingScheduler(
    private val artRankingService: ArtRankingService,
    @Value("\${scheduler.enabled}")
    private val isScheduleEnabled: Boolean,
) {
    @Scheduled(cron = "0 0 * * * *")
    fun calculateArtRanking() {
        // 분산 환경에서 하나만 돌아가도록 설정함
        if (!isScheduleEnabled) return

        val now = LocalDateTime.now()

        val rankingTypes =
            ArtRankingType.entries
                .filter { it.isSatisfied(now) }

        val rankings = artRankingService.fetchArtRanking()

        rankingTypes.forEach {
            // TODO: 캐시 저장
            println(it)
        }
    }
}

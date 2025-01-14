package octoping.ticketing.domain.ranking.service

import octoping.ticketing.domain.ranking.model.ArtRankingType
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class ArtRankingScheduler(
    private val artRankingService: ArtRankingService,
) {
    // TODO: 분산 환경에서 하나만 돌아가도록 설정 필요
//    @Scheduled(cron = "0 0 * * * *")
    fun calculateArtRanking() {
        val now = LocalDateTime.now()

        val rankingTypes = ArtRankingType.entries
            .filter { it.isSatisfied(now) }

        val rankings = artRankingService.fetchArtRanking()

        rankingTypes.forEach {
            // TODO: 캐시 저장
            println(it)
        }
    }
}
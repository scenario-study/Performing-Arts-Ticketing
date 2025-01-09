package octoping.ticketing.domain.artview.service

import octoping.ticketing.domain.arts.model.Art
import octoping.ticketing.domain.artview.model.ArtView
import octoping.ticketing.domain.artview.repository.ArtViewRepository
import java.time.LocalDateTime

class ArtViewService(
    private val artViewRepository: ArtViewRepository,
) {
    fun plusArtView(userIP: String, userId: Long?, art: Art, now: LocalDateTime = LocalDateTime.now()) {
        // TODO: Redis 같은 캐싱 기법 활용 여지 있음
        val prevArtView = artViewRepository.findByUserAndDate(userIP, userId, now)

        if (prevArtView != null) {
            return
        }

        artViewRepository.save(
            ArtView(
                artId = art.id,
                userIP = userIP,
                userId = userId,
                visitedAt = now
            )
        )
    }
}
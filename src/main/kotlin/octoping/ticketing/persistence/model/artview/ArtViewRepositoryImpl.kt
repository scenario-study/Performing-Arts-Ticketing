package octoping.ticketing.persistence.model.artview

import octoping.ticketing.domain.artview.model.ArtView
import octoping.ticketing.domain.artview.repository.ArtViewRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ArtViewRepositoryImpl(
    private val artViewJpaRepository: ArtViewJpaRepository,
) : ArtViewRepository {
    override fun save(artView: ArtView): ArtView = ArtViewEntity.from(artView).let { artViewJpaRepository.save(it).toArtView() }

    override fun findByUserAndDate(
        userIP: String,
        userId: Long?,
        date: LocalDateTime,
    ): ArtView? =
        if (userId == null) {
            artViewJpaRepository.findByUserIPAndDate(userIP, date)?.toArtView()
        } else {
            artViewJpaRepository.findByUserIdAndDate(userId, date)?.toArtView()
        }

    override fun getViewCountByArtBetween(
        artId: Long,
        startDate: LocalDateTime,
        endDate: LocalDateTime,
    ): Long {
        TODO("Not yet implemented")
    }
}

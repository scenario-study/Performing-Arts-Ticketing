package octoping.ticketing.persistence.model.art

import octoping.ticketing.domain.arts.model.Art
import octoping.ticketing.domain.arts.repository.ArtRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ArtRepositoryImpl(
    private val artJpaRepository: ArtJpaRepository,
) : ArtRepository {
    override fun findById(id: Long): Art? = artJpaRepository.findByIdOrNull(id)?.toArt()

    override fun save(art: Art): Art = ArtEntity.from(art).let { artJpaRepository.save(it).toArt() }

    override fun findAllBy(page: Int): List<Art> = artJpaRepository.findArtEntitiesBy(Pageable.ofSize(30).withPage(page)).map { it.toArt() }
}

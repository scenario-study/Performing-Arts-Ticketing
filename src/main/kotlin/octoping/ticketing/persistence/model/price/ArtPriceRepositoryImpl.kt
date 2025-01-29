package octoping.ticketing.persistence.model.price

import octoping.ticketing.domain.price.model.ArtPrice
import octoping.ticketing.domain.price.repository.ArtPriceRepository
import org.springframework.stereotype.Component

@Component
class ArtPriceRepositoryImpl(
    private val artPriceJpaRepository: ArtPriceJpaRepository,
) : ArtPriceRepository {
    override fun save(artPrice: ArtPrice): ArtPrice = artPriceJpaRepository.save(ArtPriceEntity.from(artPrice)).toArtPrice()

    override fun findByArtId(artId: Long): List<ArtPrice> {
        TODO("Not yet implemented")
    }

    override fun findRecentByArtId(artId: Long): ArtPrice? {
        TODO("Not yet implemented")
    }
}

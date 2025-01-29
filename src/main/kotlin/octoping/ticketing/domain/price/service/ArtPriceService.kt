package octoping.ticketing.domain.price.service

import octoping.ticketing.domain.exception.NotFoundException
import octoping.ticketing.domain.price.event.ArtPriceChangedEvent
import octoping.ticketing.domain.price.repository.ArtPriceRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class ArtPriceService(
    private val artPriceRepository: ArtPriceRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {
    fun changeArtPrice(
        artId: Long,
        basePrice: Long,
        discountedPrice: Long,
    ) {
        // TODO: 권한 체크

        val artPrice =
            artPriceRepository.findRecentByArtId(artId)
                ?: throw NotFoundException("ArtPrice with id $artId not found")

        val newPrice = artPrice.changePrice(basePrice, discountedPrice)

        if (newPrice == artPrice) {
            return
        }

        artPriceRepository.save(newPrice)
        artPriceRepository.save(artPrice)

        // TODO: publish된 이벤트를 받아서 Redis Pub/Sub을 통해 캐싱 만료시키기
        eventPublisher.publishEvent(
            ArtPriceChangedEvent(newPrice),
        )
    }
}

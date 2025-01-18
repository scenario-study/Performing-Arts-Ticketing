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
        // 생각: 사실 가격이 바뀌는 일보다는 썸네일, 제목이 바뀔텐데.. 인터파크는 가격을 리스트에 보여주지도 않는다. 캐싱 무효화가 이 쪽에 필요하진 않을 것 같지만, 공연 스펙 변경 api는 존재하지 않으므로 여기에 기능을 둠.
        eventPublisher.publishEvent(
            ArtPriceChangedEvent(newPrice),
        )
    }
}

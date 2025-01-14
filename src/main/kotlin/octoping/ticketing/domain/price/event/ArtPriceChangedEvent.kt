package octoping.ticketing.domain.price.event

import octoping.ticketing.domain.price.model.ArtPrice

data class ArtPriceChangedEvent(
    val artPrice: ArtPrice,
)

package octoping.ticketing.api.controller.art.request

data class ArtChangePriceRequestDTO(
    val basePrice: Long,
    val discountPrice: Long,
)

package octoping.ticketing.api.controller.art

import octoping.ticketing.api.controller.art.request.ArtChangePriceRequestDTO
import octoping.ticketing.api.controller.art.request.ArtInfoResponseDTO
import octoping.ticketing.api.controller.art.request.ArtListResponseDTO
import octoping.ticketing.domain.arts.service.ArtService
import octoping.ticketing.domain.price.service.ArtPriceService
import org.springframework.web.bind.annotation.*

@RestController
class ArtController(
    private val artService: ArtService,
    private val priceService: ArtPriceService,
) {
    @GetMapping("/arts/{artId}")
    fun getArtsInfo(
        @PathVariable artId: String,
    ): ArtInfoResponseDTO {
        TODO()
    }

    @PutMapping("/arts/{artId}/price")
    fun changePrice(
        @PathVariable artId: Long,
        @RequestBody request: ArtChangePriceRequestDTO
    ) {
        priceService.changeArtPrice(
            artId = artId,
            basePrice = request.basePrice,
            discountedPrice = request.discountPrice
        )
    }

    @GetMapping("/arts/ranking/popular")
    fun getPopularArts(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "range", defaultValue = "WEEK") range: String,
    ): ArtListResponseDTO {
        return ArtListResponseDTO(emptyList(), page)
    }
}

package octoping.ticketing.api.controller.art

import io.swagger.v3.oas.annotations.Operation
import jakarta.servlet.http.HttpServletRequest
import octoping.ticketing.api.controller.art.request.ArtChangePriceRequestDTO
import octoping.ticketing.api.controller.art.request.ArtInfoResponseDTO
import octoping.ticketing.api.controller.art.request.ArtListResponseDTO
import octoping.ticketing.domain.arts.service.ArtService
import octoping.ticketing.domain.artview.service.ArtViewService
import octoping.ticketing.domain.price.service.ArtPriceService
import org.springframework.web.bind.annotation.*

@RestController
class ArtController(
    private val artService: ArtService,
    private val priceService: ArtPriceService,
    private val artViewService: ArtViewService,
) {
    @Operation(summary = "공연 상세 조회")
    @GetMapping("/arts/{artId}")
    fun getArtsInfo(
        @PathVariable artId: Long,
    ): ArtInfoResponseDTO {
        val artInfo = artService.getInfoById(artId)

        return ArtInfoResponseDTO(
            id = artInfo.id,
            name = artInfo.name,
            basePrice = artInfo.basePrice,
            discountedPrice = artInfo.discountedPrice,
            startDate = artInfo.startDate,
            endDate = artInfo.endDate,
        )
    }

    @Operation(summary = "공연 목록 조회")
    @GetMapping("/arts")
    fun getArts(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
    ): ArtListResponseDTO {
        val arts = artService.getList(page)
        return ArtListResponseDTO(arts, page)
    }

    @Operation(summary = "공연 가격 변경")
    @PutMapping("/arts/{artId}/price")
    fun changePrice(
        @PathVariable artId: Long,
        @RequestBody request: ArtChangePriceRequestDTO,
    ) {
        priceService.changeArtPrice(
            artId = artId,
            basePrice = request.basePrice,
            discountedPrice = request.discountPrice,
        )
    }

    @Operation(summary = "인기 공연 조회")
    @GetMapping("/arts/ranking/popular")
    fun getPopularArts(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "range", defaultValue = "WEEK") range: String,
    ): ArtListResponseDTO = ArtListResponseDTO(emptyList(), page)

    @Operation(summary = "공연 조회")
    @PostMapping("/arts/{artId}/view")
    fun viewArt(
        @PathVariable artId: Long,
        request: HttpServletRequest,
    ) {
        artViewService.plusArtView(
            userIP = request.remoteAddr,
            userId = null, // TODO: 로그인 기능 추가되면 수정
            artId = artId,
        )
    }
}

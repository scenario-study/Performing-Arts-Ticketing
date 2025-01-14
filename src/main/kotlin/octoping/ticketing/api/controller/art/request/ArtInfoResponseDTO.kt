package octoping.ticketing.api.controller.art.request

import java.time.LocalDateTime

data class ArtInfoResponseDTO(
    val id: Long,
    val title: String,
    val basePrice: Long,
    val discountedPrice: Long,
    val artDate: LocalDateTime,
    val createdAt: LocalDateTime,
)

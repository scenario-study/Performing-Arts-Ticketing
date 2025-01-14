package octoping.ticketing.api.controller.art.schema

import java.time.LocalDateTime

data class ArtItemSchema(
    val id: Long,
    val title: String,
    val basePrice: Long,
    val discountedPrice: Long,
    val artDate: LocalDateTime,
    val createdAt: LocalDateTime,
)

package octoping.ticketing.domain.arts.schema

import java.time.LocalDate

data class ArtInfo(
    val id: Long,
    val name: String,
    val basePrice: Long,
    val discountedPrice: Long,
    val startDate: LocalDate,
    val endDate: LocalDate?,
)

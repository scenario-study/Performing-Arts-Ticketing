package octoping.ticketing.api.controller.art.schema

import java.time.LocalDate

data class ArtItemSchema(
    val id: Long,
    val name: String,
    val startDate: LocalDate,
    val endDate: LocalDate?,
)

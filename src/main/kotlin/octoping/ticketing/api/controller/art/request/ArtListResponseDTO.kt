package octoping.ticketing.api.controller.art.request

import octoping.ticketing.api.controller.art.schema.ArtItemSchema

data class ArtListResponseDTO(
    val arts: List<ArtItemSchema>,
    val page: Int,
) {
}

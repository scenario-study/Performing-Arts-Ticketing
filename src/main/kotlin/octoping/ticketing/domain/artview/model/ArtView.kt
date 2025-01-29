package octoping.ticketing.domain.artview.model

import java.time.LocalDateTime

data class ArtView(
    val id: Long = 0,
    val artId: Long,
    val userIP: String,
    val userId: Long?,
    val visitedAt: LocalDateTime = LocalDateTime.now(),
)

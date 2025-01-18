package octoping.ticketing.persistence.model.artview

import jakarta.persistence.Entity
import octoping.ticketing.domain.artview.model.ArtView
import octoping.ticketing.persistence.common.BaseEntity
import java.time.LocalDateTime

@Entity
class ArtViewEntity(
    id: Long = 0,
    var artId: Long,
    var userIP: String,
    var userId: Long?,
    var visitedAt: LocalDateTime = LocalDateTime.now(),
) : BaseEntity(id) {
    fun toArtView() =
        ArtView(
            id,
            artId,
            userIP,
            userId,
            visitedAt,
        )

    companion object {
        fun from(artView: ArtView) =
            ArtViewEntity(
                artView.id,
                artView.artId,
                artView.userIP,
                artView.userId,
                artView.visitedAt,
            )
    }
}

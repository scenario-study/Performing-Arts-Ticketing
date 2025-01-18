package octoping.ticketing.persistence.model.artview

import jakarta.persistence.Column
import jakarta.persistence.Entity
import octoping.ticketing.domain.artview.model.ArtView
import octoping.ticketing.persistence.common.BaseEntity
import java.time.LocalDateTime

@Entity
class ArtViewEntity(
    id: Long = 0,
    var artId: Long,
    @Column(name = "user_ip")
    var userIP: String,
    @Column(name = "user_id")
    var userId: Long?,
    @Column(name = "visited_at")
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

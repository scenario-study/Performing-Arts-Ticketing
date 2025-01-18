package octoping.ticketing.persistence.model.art

import jakarta.persistence.Entity
import octoping.ticketing.domain.arts.model.Art
import octoping.ticketing.persistence.common.BaseEntity
import java.time.LocalDate

@Entity
class ArtEntity(
    id: Long = 0,
    var name: String,
    var description: String,
    var onePersonBuyLimit: Int,
    var startDate: LocalDate,
    var endDate: LocalDate? = null,
) : BaseEntity(id) {
    fun toArt() =
        Art(
            id,
            name,
            description,
            onePersonBuyLimit,
            startDate,
            endDate,
        )

    companion object {
        fun from(art: Art) =
            ArtEntity(
                art.id,
                art.name,
                art.description,
                art.onePersonBuyLimit,
                art.startDate,
                art.endDate,
            )
    }
}

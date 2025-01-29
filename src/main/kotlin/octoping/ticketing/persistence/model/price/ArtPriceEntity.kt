package octoping.ticketing.persistence.model.price

import jakarta.persistence.Column
import jakarta.persistence.Entity
import octoping.ticketing.domain.price.model.ArtPrice
import octoping.ticketing.persistence.common.BaseEntity
import java.time.LocalDateTime

@Entity
class ArtPriceEntity(
    id: Long = 0,
    @Column(name = "art_id", nullable = false)
    var artId: Long,
    @Column(name = "base_price", nullable = false)
    var basePrice: Long,
    @Column(name = "discounted_price")
    var discountedPrice: Long,
    @Column(name = "start_date", nullable = false)
    var startDate: LocalDateTime,
    @Column(name = "end_date")
    var endDate: LocalDateTime?,
) : BaseEntity(id) {
    fun toArtPrice() =
        ArtPrice(
            id = id,
            artId = artId,
            basePrice = basePrice,
            discountedPrice = discountedPrice,
            startDate = startDate,
            endDate = endDate,
        )

    companion object {
        fun from(artPrice: ArtPrice) =
            ArtPriceEntity(
                id = artPrice.id,
                artId = artPrice.artId,
                basePrice = artPrice.basePrice,
                discountedPrice = artPrice.discountedPrice,
                startDate = artPrice.startDate,
                endDate = artPrice.endDate,
            )
    }
}

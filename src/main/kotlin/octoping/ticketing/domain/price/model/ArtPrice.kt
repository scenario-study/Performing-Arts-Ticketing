package octoping.ticketing.domain.price.model

import octoping.ticketing.domain.exception.ValidationException
import java.time.LocalDateTime

private const val MAX_PRICE = 10_000_000_000

class ArtPrice(
    id: Long = 0,
    val artId: Long,
    val basePrice: Long,
    val discountedPrice: Long,
    val startDate: LocalDateTime = LocalDateTime.now(),
    endDate: LocalDateTime? = null,
) {
    private var _id: Long
    private var _endDate: LocalDateTime?

    val id get() = _id
    val endDate get() = _endDate

    init {
        if (basePrice < 0) {
            throw ValidationException("Price cannot be negative: $basePrice")
        }

        if (basePrice > MAX_PRICE) {
            throw ValidationException("Price cannot be greater than max price: $basePrice")
        }

        if (discountedPrice < 0) {
            throw ValidationException("Discount cannot be negative: $discountedPrice")
        }

        if (discountedPrice > basePrice) {
            throw ValidationException("Discount cannot be less than max price: $discountedPrice")
        }

        _id = id
        _endDate = endDate
    }

    fun changePrice(
        basePrice: Long,
        discountedPrice: Long,
        now: LocalDateTime = LocalDateTime.now(),
    ): ArtPrice {
        if (!this.isCurrentlyApplied()) {
            throw ValidationException("현재 가격이 변경되었습니다")
        }

        if (this.basePrice == basePrice && this.discountedPrice == discountedPrice) {
            return this
        }

        this._endDate = now

        return ArtPrice(
            artId = artId,
            basePrice = basePrice,
            discountedPrice = discountedPrice,
            startDate = startDate,
            endDate = endDate,
        )
    }

    fun getDiscountRate(): Int {
        if (this.basePrice == 0L) {
            return 0
        }

        return (this.discountedPrice / this.basePrice * 100).toInt()
    }

    fun isCurrentlyApplied() = this.endDate != null
}

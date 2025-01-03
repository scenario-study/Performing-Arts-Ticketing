package octoping.ticketing.domain.arts.model

import octoping.ticketing.domain.exception.ValidationException

private const val MAX_PRICE = 10_000_000_000

data class ArtPrice(
    val price: Long,
) {
    init {
        if (price < 0) {
            throw ValidationException("Price cannot be negative: $price")
        }

        if (price > MAX_PRICE) {
            throw ValidationException("Price cannot be greater than max price: $price")
        }
    }
}
package octoping.ticketing.domain.discount.model

interface DiscountCoupon {
    fun discount(originalPrice: Long): Long
}
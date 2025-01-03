package octoping.ticketing.domain.discount

interface DiscountCoupon {
    fun discount(originalPrice: Long): Long
}
package octoping.ticketing.domain.discount.model

class NoDiscountCoupon : DiscountCoupon {
    override fun discount(originalPrice: Long) = originalPrice
}
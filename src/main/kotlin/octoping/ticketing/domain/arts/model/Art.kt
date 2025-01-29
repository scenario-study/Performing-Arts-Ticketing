package octoping.ticketing.domain.arts.model

import octoping.ticketing.domain.discount.model.DiscountCoupon
import octoping.ticketing.domain.discount.model.NoDiscountCoupon
import octoping.ticketing.domain.exception.ValidationException
import octoping.ticketing.domain.price.model.ArtPrice
import octoping.ticketing.domain.ticket.model.Ticket
import octoping.ticketing.domain.users.model.User
import java.time.LocalDate

class Art(
    id: Long,
    name: String,
    description: String,
    onePersonBuyLimit: Int,
    startDate: LocalDate,
    endDate: LocalDate? = null,
) {
    private val _id: Long
    private var _name: ArtName
    private var _description: String
    private var _onePersonBuyLimit: Int
    private var artDate: ArtDate

    init {
        validate(description)
        this._id = id
        this._name = ArtName(name)
        this._description = description
        this._onePersonBuyLimit = onePersonBuyLimit
        this.artDate = ArtDate(startDate, endDate)
    }

    val id get() = _id
    val name get() = _name.name
    val description get() = _description
    val onePersonBuyLimit get() = _onePersonBuyLimit

    val startDate get() = artDate.startDate
    val endDate get() = artDate.endDate

    fun buyTicket(
        user: User,
        price: ArtPrice,
        discountCoupon: DiscountCoupon = NoDiscountCoupon(),
    ): Ticket {
        if (user.isNew()) {
            throw ValidationException("저장되지 않은 유저입니다")
        }

        if (!price.isCurrentlyApplied()) {
            throw ValidationException("현재 유효하지 않은 가격입니다")
        }

        return Ticket(
            artId = this.id,
            originalPrice = price.basePrice,
            boughtPrice = discountCoupon.discount(price.discountedPrice),
            boughtUserId = user.id,
        )
    }

    private fun validate(description: String) {
        if (description.length > MAX_DESCRIPTION_LENGTH) {
            throw ValidationException("Description cannot be greater than max description: $description")
        }
    }

    companion object {
        private const val MAX_DESCRIPTION_LENGTH = 100000
    }
}

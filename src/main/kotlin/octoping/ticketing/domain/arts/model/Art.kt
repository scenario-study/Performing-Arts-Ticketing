package octoping.ticketing.domain.arts.model

import octoping.ticketing.domain.discount.DiscountCoupon
import octoping.ticketing.domain.exception.ValidationException
import octoping.ticketing.domain.ticket.model.Ticket
import octoping.ticketing.domain.users.model.User
import java.time.LocalDate

abstract class Art(
    id: Long,
    name: String,
    description: String,
    price: Long,
    onePersonBuyLimit: Int,
    startDate: LocalDate,
    endDate: LocalDate,
) {
    private val _id: Long
    private var _name: ArtName
    private var _description: String
    private var _price: ArtPrice
    private var _onePersonBuyLimit: Int
    private var _artDate: ArtDate

    init {
        validate(name)
        this._id = id
        this._name = ArtName(name)
        this._description = description
        this._price = ArtPrice(price)
        this._onePersonBuyLimit = onePersonBuyLimit
        this._artDate = ArtDate(startDate, endDate)
    }

    val name get() = _name.name
    val description get() = _description
    val price get() = this._price.price
    val onePersonBuyLimit get() = _onePersonBuyLimit

    fun buyTicket(user: User, discountCoupon: DiscountCoupon): Ticket {
        if (user.isNew()) {
            throw ValidationException("저장되지 않은 유저입니다")
        }

        return Ticket(
            originalPrice = this.price,
            boughtPrice = discountCoupon.discount(this.price),
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
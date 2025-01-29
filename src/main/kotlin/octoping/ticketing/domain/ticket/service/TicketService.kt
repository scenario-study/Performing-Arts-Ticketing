package octoping.ticketing.domain.ticket.service

import octoping.ticketing.domain.arts.model.Art
import octoping.ticketing.domain.arts.repository.ArtRepository
import octoping.ticketing.domain.discount.model.DiscountCoupon
import octoping.ticketing.domain.price.model.ArtPrice
import octoping.ticketing.domain.ticket.model.Ticket
import octoping.ticketing.domain.users.model.User

class TicketService(
    private val artRepository: ArtRepository
) {
    fun buyTicket(art: Art, price: ArtPrice, user: User, discountCoupon: DiscountCoupon): Ticket {
        val ticket = art.buyTicket(user, price, discountCoupon)

        return ticket;
    }
}
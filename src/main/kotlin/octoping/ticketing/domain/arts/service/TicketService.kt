package octoping.ticketing.domain.arts.service

import octoping.ticketing.domain.arts.model.Art
import octoping.ticketing.domain.arts.repository.ArtRepository
import octoping.ticketing.domain.discount.DiscountCoupon
import octoping.ticketing.domain.ticket.model.Ticket
import octoping.ticketing.domain.users.model.User

class TicketService(
    private val artRepository: ArtRepository
) {
    fun buyTicket(art: Art, user: User, discountCoupon: DiscountCoupon): Ticket {
        val ticket = art.buyTicket(user, discountCoupon)

        return ticket
    }
}
package octoping.ticketing.persistence.model.ticket

import octoping.ticketing.domain.ticket.model.Ticket
import octoping.ticketing.domain.ticket.repository.TicketRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class TicketRepositoryImpl(
    private val ticketJpaRepository: TicketJpaRepository,
) : TicketRepository {
    override fun findById(id: Long): Ticket? = ticketJpaRepository.findByIdOrNull(id)?.toTicket()

    override fun save(ticket: Ticket): Ticket = TicketEntity.from(ticket).let { ticketJpaRepository.save(it).toTicket() }
}

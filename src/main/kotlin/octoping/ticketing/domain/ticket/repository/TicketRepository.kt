package octoping.ticketing.domain.ticket.repository

import octoping.ticketing.domain.ticket.model.Ticket

interface TicketRepository {
    fun findById(id: Long): Ticket?
    
    fun save(ticket: Ticket): Ticket
}
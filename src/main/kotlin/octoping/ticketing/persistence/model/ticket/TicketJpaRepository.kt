package octoping.ticketing.persistence.model.ticket

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketJpaRepository : JpaRepository<TicketEntity, Long> {
}

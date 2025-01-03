package octoping.ticketing.domain.arts.repository

import octoping.ticketing.domain.arts.model.Art

interface ArtRepository {
    fun findById(id: Long): Art?
}
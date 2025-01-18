package octoping.ticketing.persistence.model.art

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ArtJpaRepository : JpaRepository<ArtEntity, Long> {
    @Query(
        """
        SELECT a
        FROM ArtEntity a
        ORDER BY a.id DESC
    """,
    )
    fun findArtEntitiesBy(page: Pageable): List<ArtEntity>
}

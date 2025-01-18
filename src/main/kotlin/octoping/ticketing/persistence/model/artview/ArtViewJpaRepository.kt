package octoping.ticketing.persistence.model.artview

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ArtViewJpaRepository : JpaRepository<ArtViewEntity, Long> {
    @Query(
        """
        SELECT a
        FROM ArtViewEntity a
        WHERE a.userIP = :userIP
        AND a.visitedAt = :date
    """,
    )
    fun findByUserIPAndDate(
        userIP: String,
        date: LocalDateTime,
    ): ArtViewEntity?

    @Query(
        """
        SELECT a
        FROM ArtViewEntity a
        WHERE a.userId = :userId
        AND a.visitedAt = :date
    """,
    )
    fun findByUserIdAndDate(
        userId: Long,
        date: LocalDateTime,
    ): ArtViewEntity?
}

package com.performance.web.api.reservation.infrastructure.jpa

import com.performance.web.api.reservation.domain.Session
import jakarta.persistence.*
import org.hibernate.Hibernate
import java.time.LocalDateTime

@Entity
@Table(name = "performance_session")
class SessionEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "performance_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var performance: PerformanceEntity,

    @Column(nullable = false)
    var startDateTime: LocalDateTime,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "session")
    var seats: MutableList<SeatEntity> = mutableListOf(),

    ) {


    fun toDomain(): Session {
        if (!Hibernate.isInitialized(seats)) {
            return Session(
                id = id,
                performance = performance.toDomain(),
                startDateTime = startDateTime,
            )
        }

        return Session(
            id = id,
            performance = performance.toDomain(),
            startDateTime = startDateTime,
            seats = seats.map {
                it.toDomain()
            },
        )
    }


    companion object {
        fun fromDomain(session: Session): SessionEntity {
            return SessionEntity(
                id = session.getId(),
                performance = PerformanceEntity.fromDomain(session.getPerformance()),
                startDateTime = session.getStartDateTime(),
                seats = session.getSeats().map { SeatEntity.fromDomain(it) }.toMutableList()
            )
        }
    }
}

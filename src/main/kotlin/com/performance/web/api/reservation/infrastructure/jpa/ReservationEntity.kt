package com.performance.web.api.reservation.infrastructure.jpa

import com.performance.web.api.reservation.domain.Customer
import com.performance.web.api.reservation.domain.Reservation
import jakarta.persistence.*
import org.hibernate.Hibernate

@Entity
@Table(name = "reservation")
class ReservationEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var session: SessionEntity,

    var customerId: Long,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation", cascade = [CascadeType.PERSIST], orphanRemoval = true)
    var tickets: MutableList<TicketEntity> = mutableListOf()
    ) {

    fun toDomain() : Reservation {
        if(!Hibernate.isInitialized(tickets)){
            return Reservation(
                id = id!!,
                session = session.toDomain(),
                customer = Customer(customerId),
            )
        }

        return Reservation(
            id = id!!,
            session = session.toDomain(),
            customer = Customer(customerId),
            tickets = tickets.map { it.toDomain() }
        )
    }


    companion object {
        fun fromDomain(reservation : Reservation) : ReservationEntity {
            val reservationEntity = ReservationEntity(
                id = if (reservation.getId() == 0L) null else reservation.getId(),
                session = SessionEntity.fromDomain(reservation.getSession()),
                customerId = reservation.getCustomer().getId(),
            )

            reservationEntity.tickets = reservation.getTickets()
                .map { TicketEntity.fromDomain(it, reservationEntity) }
                .toMutableList()

            return reservationEntity
        }
    }
}

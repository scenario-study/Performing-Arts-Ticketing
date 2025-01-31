package com.performance.web.api.reservation.infrastructure.jpa

import com.performance.web.api.common.domain.Money
import com.performance.web.api.discount.infrastructure.jpa.DiscountPolicyEntity
import com.performance.web.api.discount.infrastructure.jpa.mapper.DiscountPolicyMapper
import com.performance.web.api.reservation.domain.Ticket
import jakarta.persistence.*

@Entity
@Table(name = "ticket")
class TicketEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var totalAmount: Long,

    @Column(nullable = false)
    var regularPrice: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var seat: SeatEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_policy_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var appliedDiscountPolicy: DiscountPolicyEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var reservation: ReservationEntity,
) {

    fun toDomain(): Ticket {
        return Ticket(
            id = id!!,
            totalAmount = Money.of(totalAmount),
            regularPrice = Money.of(regularPrice),
            seat = seat.toDomain(),
            appliedDiscountPolicy = appliedDiscountPolicy.toDomain()
        )
    }

    companion object {
        fun fromDomain(ticket: Ticket, reservation: ReservationEntity): TicketEntity {
            return TicketEntity(
                id = if(ticket.getId() == 0L) null else ticket.getId(),
                totalAmount =  ticket.getTotalAmount().longValue(),
                regularPrice = ticket.getRegularPrice().longValue(),
                seat = SeatEntity.fromDomain(ticket.getSeat()),
                appliedDiscountPolicy = DiscountPolicyMapper.fromDomainToEntity(ticket.getAppliedDiscountPolicy()),
                reservation = reservation
            )
        }
    }
}

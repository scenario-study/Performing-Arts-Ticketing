package com.performance.web.api.reservation.infrastructure.jpa

import com.performance.web.api.discount.infrastructure.jpa.DiscountPolicyEntity
import jakarta.persistence.*

@Entity
@Table(name = "ticket")
class TicketEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

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

}

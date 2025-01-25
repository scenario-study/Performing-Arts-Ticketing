package com.performance.web.api.discount.infrastructure.jpa

import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.reservation.infrastructure.jpa.SeatClassEntity
import jakarta.persistence.*

@Entity
@Table(name = "discount_policy")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "policy_type")
abstract class DiscountPolicyEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Column(name = "name", nullable = false)
    var name: String,

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "discountPolicy", fetch = FetchType.LAZY, orphanRemoval = true)
    var conditions: MutableList<DiscountConditionEntity> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_class_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var seatClass: SeatClassEntity?,
) {


    abstract fun toDomain(): DiscountPolicy

}

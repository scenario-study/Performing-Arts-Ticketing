package com.performance.web.api.reservation.infrastructure.jpa

import com.performance.web.api.common.domain.Money
import com.performance.web.api.discount.infrastructure.jpa.DiscountPolicyEntity
import com.performance.web.api.discount.infrastructure.jpa.mapper.DiscountPolicyMapper
import com.performance.web.api.reservation.domain.SeatClass
import jakarta.persistence.*
import org.hibernate.Hibernate

@Entity
@Table(name = "seat_class")
class SeatClassEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false)
    var price: Long,

    @Column(nullable = false)
    var classType: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var performance: PerformanceEntity? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seatClass")
    var discountPolicies: MutableList<DiscountPolicyEntity> = mutableListOf()
) {

    fun toDomain(): SeatClass {
        if(!Hibernate.isInitialized(discountPolicies)){
            return SeatClass(
                price = Money.of(price),
                classType = classType,
            )
        }
        return SeatClass(
            price = Money.of(price),
            classType = classType,
            discountPolicies = discountPolicies.map { it.toDomain() },
        )
    }

    companion object {
        fun fromDomain(seatClass: SeatClass): SeatClassEntity {
            return SeatClassEntity(
                id = seatClass.getId(),
                price = seatClass.getPrice().longValue(),
                classType = seatClass.getClassType(),
                discountPolicies = seatClass.getDiscountPolicies().map { DiscountPolicyMapper.fromDomainToEntity(it) }
                    .toMutableList(),
            )
        }
    }
}

package com.performance.web.api.discount.infrastructure.jpa

import com.performance.web.api.discount.domain.DiscountPolicy
import com.performance.web.api.discount.domain.PercentDiscountPolicy
import com.performance.web.api.reservation.infrastructure.jpa.SeatClassEntity
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import org.hibernate.Hibernate

@Entity
@DiscriminatorValue("PERCENT")
class PercentDiscountPolicyEntity(
    id : Long,
    name: String,
    conditions: MutableList<DiscountConditionEntity> = mutableListOf(),
    seatClass: SeatClassEntity?,

    @Column(name = "percent", nullable = true)
    var percent: Double
) : DiscountPolicyEntity(
    id = id,
    name = name,
    conditions = conditions,
    seatClass = seatClass,
){

    override fun toDomain(): DiscountPolicy {
        if(!Hibernate.isInitialized(conditions)){
            return PercentDiscountPolicy(
                id = this.id ?: 0L,
                name = this.name,
                percent = this.percent,
            )
        }

        return PercentDiscountPolicy(
            id = this.id ?: 0L,
            name = this.name,
            percent = this.percent,
            conditions = this.conditions.map { it.toDomain() }.toTypedArray()
        )
    }
}

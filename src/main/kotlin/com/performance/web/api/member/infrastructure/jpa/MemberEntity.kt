package com.performance.web.api.member.infrastructure.jpa

import com.performance.web.api.member.domain.Member
import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var name: String

) {

    fun toDomain(): Member {
        return Member(
            id = id,
            name = name,
        )
    }


    companion object {
        fun fromDomain(customer: Member): MemberEntity {
            return MemberEntity(
                id = customer.getId(),
                name = customer.getName(),
            )
        }
    }
}

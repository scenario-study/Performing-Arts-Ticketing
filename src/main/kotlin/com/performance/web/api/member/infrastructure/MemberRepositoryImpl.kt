package com.performance.web.api.member.infrastructure

import com.performance.web.api.member.domain.Member
import com.performance.web.api.member.domain.MemberRepository
import com.performance.web.api.member.infrastructure.jpa.MemberJpaRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository
) : MemberRepository {

    override fun findById(id: Long): Optional<Member> {
        return memberJpaRepository.findById(id).map { it.toDomain() }
    }
}

package com.performance.web.api.member.infrastructure

import com.performance.web.api.member.domain.Member
import com.performance.web.api.member.domain.MemberRepository
import java.util.Collections
import java.util.Optional

class MemoryBasedMemberRepositoryImpl : MemberRepository {

    private final val store =
        Collections.synchronizedMap(
            mutableMapOf<Long, Member>(
                1L to Member(1L, "조인혁"),
            ),
        )

    override fun findById(id: Long): Optional<Member> = Optional.ofNullable(store[id])
}

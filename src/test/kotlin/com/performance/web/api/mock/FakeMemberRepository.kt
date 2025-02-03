package com.performance.web.api.mock

import com.performance.web.api.member.domain.Member
import com.performance.web.api.member.domain.MemberRepository
import java.util.*

class FakeMemberRepository : MemberRepository {

    override fun findById(id: Long): Optional<Member> {
        TODO("Not yet implemented")
    }
}

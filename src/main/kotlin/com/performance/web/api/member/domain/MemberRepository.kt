package com.performance.web.api.member.domain

import java.util.Optional

interface MemberRepository {

    fun findById(id: Long): Optional<Member>
}

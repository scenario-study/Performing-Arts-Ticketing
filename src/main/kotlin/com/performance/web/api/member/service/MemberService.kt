package com.performance.web.api.member.service

import com.performance.web.api.common.domain.ResourceNotFoundException
import com.performance.web.api.member.domain.Member
import com.performance.web.api.member.domain.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {

    fun findById(id: Long): Member =
        memberRepository.findById(id)
            .orElseThrow { throw ResourceNotFoundException("Member with id $id not found") }
}

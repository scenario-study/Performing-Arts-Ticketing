package com.hunhui.ticketworld.web.controller.doc

import com.hunhui.ticketworld.application.dto.response.UserCreateResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "User", description = "유저 관련 API")
interface UserApiDoc {
    @Operation(summary = "유저 생성 API")
    fun createUser(): ResponseEntity<UserCreateResponse>
}

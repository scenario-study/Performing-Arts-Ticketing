package com.cd18.web.controller

import com.cd18.common.http.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController {
    @Operation(summary = "Ping API", description = "Ping API")
    @GetMapping("/ping")
    fun ping(): ApiResponse<String> {
        return ApiResponse(result = "pong")
    }
}

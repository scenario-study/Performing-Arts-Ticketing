package com.hunhui.api.controller

import com.hunhui.api.controller.doc.SampleApiDoc
import com.hunhui.api.controller.exception.SampleException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController : SampleApiDoc {
    @GetMapping("/ping")
    override fun ping(): ResponseEntity<SampleResponse> = ResponseEntity.ok(SampleResponse("pong"))

    @GetMapping("/error")
    override fun error(): Unit = throw SampleException()

    data class SampleResponse(
        val message: String,
    )
}

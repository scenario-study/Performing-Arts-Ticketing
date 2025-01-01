package com.hunhui.api.controller.doc

import com.hunhui.api.controller.SampleController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "Sample", description = "Sample API")
interface SampleApiDoc {
    @Operation(summary = "Ping API")
    fun ping(): ResponseEntity<SampleController.SampleResponse>

    @Operation(summary = "Error API")
    fun error()
}

package com.performance.web.api

import com.performance.web.api.common.infrastructure.Application
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [Application::class])
class ApplicationTests {

    @Test
    fun contextLoads() {
    }
}

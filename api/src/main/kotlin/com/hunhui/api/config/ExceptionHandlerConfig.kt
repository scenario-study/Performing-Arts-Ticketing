package com.hunhui.api.config

import com.hunhui.common.error.GlobalExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExceptionHandlerConfig {
    @Bean
    fun globalExceptionHandler(): GlobalExceptionHandler {
        return GlobalExceptionHandler()
    }
}

package com.performance.web.api.common.controller

import com.performance.web.api.common.domain.BusinessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException): ResponseEntity<Any?> =
        ResponseEntity.status(400)
            .body(ex.message)
}

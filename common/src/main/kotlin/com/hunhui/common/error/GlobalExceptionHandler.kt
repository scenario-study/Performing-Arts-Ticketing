package com.hunhui.common.error

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(Exception::class)
    protected fun handleException(e: Exception): ErrorResponse {
        logger.error(e.message, e)
        return ErrorResponse.from(GlobalErrorCode.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(BusinessException::class)
    protected fun handleRuntimeException(e: BusinessException): ErrorResponse {
        logger.warn(e.message, e)
        return ErrorResponse.from(e.errorCode)
    }

    @ExceptionHandler(AccessDeniedException::class)
    protected fun handleAccessDeniedException(
        e: AccessDeniedException,
        request: HttpServletRequest,
    ): ErrorResponse {
        logger.warn(e.message, e)
        return ErrorResponse.from(GlobalErrorCode.ACCESS_DENIED)
    }
}

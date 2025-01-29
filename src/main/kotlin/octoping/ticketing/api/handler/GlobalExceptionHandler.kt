package octoping.ticketing.api.handler

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.ConstraintViolationException
import octoping.ticketing.api.config.web.ErrorResponse
import octoping.ticketing.domain.exception.DomainException
import octoping.ticketing.domain.exception.NotFoundException
import octoping.ticketing.domain.exception.ValidationException
import octoping.ticketing.utils.StacktraceParser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    private val log = KotlinLogging.logger {}

    @ExceptionHandler(
        AccessDeniedException::class,
    )
    fun handleUserNotAuthorizedException(exception: AccessDeniedException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(exception.message))

    @ExceptionHandler(
        IllegalStateException::class,
        IllegalArgumentException::class,
        ValidationException::class,
    )
    fun handleIllegalArgumentException(exception: Exception): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(exception.message))

    @ExceptionHandler(
        NotFoundException::class,
    )
    fun handleNotFoundException(exception: NotFoundException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(exception.message))

    @ExceptionHandler(DomainException::class)
    fun handleDomainException(exception: DomainException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(exception.message))

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(exception: ConstraintViolationException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(exception.constraintViolations.first().message))

    @ExceptionHandler(Exception::class)
    fun handleNotHandledException(exception: Exception): ResponseEntity<ErrorResponse> {
        val stacktrace = StacktraceParser.parseMeaningful(exception, separator = "   ")
        log.error { "예상치 못한 예외가 발생했습니다." }
        log.error { exception.message }
        log.error { stacktrace }

        return ResponseEntity
            .internalServerError()
            .body(ErrorResponse("알 수 없는 문제가 발생했습니다."))
    }
}

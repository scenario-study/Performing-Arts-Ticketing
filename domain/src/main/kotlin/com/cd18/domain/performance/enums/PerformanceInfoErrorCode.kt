package com.cd18.domain.performance.enums

import com.cd18.common.http.response.code.ErrorCode
import org.springframework.http.HttpStatus

enum class PerformanceInfoErrorCode(
    override val message: String,
    override val status: HttpStatus,
) : ErrorCode {
    NOT_FOUND("공연 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INVALID_DISCOUNT_PRICE_NOT_POSITIVE("할인 금액은 0보다 커야 합니다.", HttpStatus.BAD_REQUEST),
    INVALID_DISCOUNT_PRICE_OVER_ORIGIN_PRICE("할인 금액은 기본 금액보다 작아야 합니다.", HttpStatus.BAD_REQUEST),
    INVALID_DISCOUNT_PRICE_SAME("할인 금액이 기존 할인 금액과 같습니다.", HttpStatus.BAD_REQUEST),

    ;

    override val prefix: String = "PERF_INFO"
}

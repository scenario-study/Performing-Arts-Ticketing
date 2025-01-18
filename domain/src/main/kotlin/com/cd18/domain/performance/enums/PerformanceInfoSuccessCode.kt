package com.cd18.domain.performance.enums

import com.cd18.common.http.response.code.SuccessCode

enum class PerformanceInfoSuccessCode(
    override val message: String,
) : SuccessCode {
    CHANGE_DISCOUNT_PRICE("할인 금액 변경 성공하셨습니다."),
    ;

    override val prefix: String = "PERF_INFO"
}

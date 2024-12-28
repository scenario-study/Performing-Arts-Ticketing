package com.cd18.common.http.response.code

import org.springframework.http.HttpStatus

enum class BaseSuccessCode(
    override val message: String,
) : SuccessCode {
    OK("요청에 성공하였습니다."),
    ;

    override val prefix: String = "BASE"
}

enum class BaseErrorCode(
    override val message: String,
    override val status: HttpStatus,
) : ErrorCode {
    NOT_FOUND("해당 데이터를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    METHOD_NOT_ALLOWED("허용되지 않은 메소드입니다.", HttpStatus.METHOD_NOT_ALLOWED),
    ACCESS_DENIED("접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    UNAUTHORIZED("인증되지 않은 사용자입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_PAGE_REQUEST("잘못된 페이지 요청입니다.", HttpStatus.BAD_REQUEST),
    ;

    override val prefix: String = "BASE"
}

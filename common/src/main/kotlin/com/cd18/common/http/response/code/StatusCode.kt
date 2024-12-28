package com.cd18.common.http.response.code

import org.springframework.http.HttpStatus

interface SuccessCode {
    val name: String
    val message: String
    val prefix: String
    val code: String
        get() = "${prefix}_$name"
}

interface ErrorCode {
    val name: String
    val message: String
    val status: HttpStatus
    val prefix: String
    val code: String
        get() = "${prefix}_$name"
}

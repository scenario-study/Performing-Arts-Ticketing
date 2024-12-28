package com.cd18.common.exception

import com.cd18.common.http.response.code.ErrorCode

class BaseException(
    val errorCode: ErrorCode,
) : RuntimeException()

package com.performance.web.api.common.domain

open class BusinessException(
    override val message: String,
) : RuntimeException(message)

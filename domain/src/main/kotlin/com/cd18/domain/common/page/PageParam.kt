package com.cd18.domain.common.page

data class PageParam(
    val page: Int = 0,
    val size: Int = 10,
) {
    init {
        require(page >= 0) { "Page must be greater than or equal to 0" }

        require(size in 1..20) { "Size must be between 1 and 20" }
    }
}

package com.cd18.web.controller.request

import com.cd18.domain.common.page.PageParam
import io.swagger.v3.oas.annotations.Parameter

data class PageRequest(
    @Parameter(description = "페이지 번호 (1부터 시작)", example = "0")
    val page: Int = 0,
    @Parameter(description = "페이지 당 항목 수", example = "10")
    val size: Int = 10,
//    TODO("정렬 기준")
) {
    fun toPageParam() = PageParam(page = page, size = size)
}

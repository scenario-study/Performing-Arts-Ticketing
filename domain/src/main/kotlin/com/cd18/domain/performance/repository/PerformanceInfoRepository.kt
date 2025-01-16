package com.cd18.domain.performance.repository

import com.cd18.domain.common.page.PageParam
import com.cd18.domain.performance.dto.PerformanceInfoDetailDto
import com.cd18.domain.performance.dto.PerformanceInfoDto

interface PerformanceInfoRepository {
    fun getList(pageParam: PageParam): List<PerformanceInfoDto>

    fun getById(id: Long): PerformanceInfoDetailDto
}

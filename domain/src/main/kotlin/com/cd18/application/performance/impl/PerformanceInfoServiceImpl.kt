package com.cd18.application.performance.impl

import com.cd18.application.performance.PerformanceInfoService
import com.cd18.domain.common.page.PageParam
import com.cd18.domain.performance.dto.PerformanceInfoDetailDto
import com.cd18.domain.performance.dto.PerformanceInfoDto
import com.cd18.domain.performance.repository.PerformanceInfoRepository
import org.springframework.stereotype.Service

@Service
class PerformanceInfoServiceImpl(
    private val performanceInfoRepository: PerformanceInfoRepository,
) : PerformanceInfoService {
    override fun getList(pageParam: PageParam): Result<List<PerformanceInfoDto>> =
        runCatching {
            performanceInfoRepository.getList(pageParam)
        }

    override fun getById(id: Long): Result<PerformanceInfoDetailDto> =
        runCatching {
            performanceInfoRepository.getById(id)
        }
}

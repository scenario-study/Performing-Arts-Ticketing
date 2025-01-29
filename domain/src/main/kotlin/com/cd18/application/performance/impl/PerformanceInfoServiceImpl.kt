package com.cd18.application.performance.impl

import com.cd18.application.performance.PerformanceInfoService
import com.cd18.domain.common.page.PageParam
import com.cd18.domain.metrics.annotation.LogUserAction
import com.cd18.domain.metrics.enums.ActionType
import com.cd18.domain.metrics.enums.TargetType
import com.cd18.domain.performance.dto.PerformanceInfoDetailDto
import com.cd18.domain.performance.dto.PerformanceInfoDto
import com.cd18.domain.performance.model.PerformancePrice
import com.cd18.domain.performance.repository.PerformanceInfoHistoryRepository
import com.cd18.domain.performance.repository.PerformanceInfoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PerformanceInfoServiceImpl(
    private val performanceInfoRepository: PerformanceInfoRepository,
    private val performanceInfoHistoryRepository: PerformanceInfoHistoryRepository,
) : PerformanceInfoService {
    override fun getList(pageParam: PageParam): Result<List<PerformanceInfoDto>> =
        runCatching {
            performanceInfoRepository.getList(pageParam)
        }

    @LogUserAction(
        actionType = ActionType.VIEW_PERF,
        targetType = TargetType.PERF,
        targetIdKey = "id",
        description = "Performance Info view",
    )
    override fun getById(id: Long): Result<PerformanceInfoDetailDto> =
        runCatching {
            performanceInfoRepository.getById(id)
        }

    @Transactional
    override fun changeDiscountPrice(
        id: Long,
        discountPrice: Int,
    ): Result<Unit> =
        runCatching {
            val currentPriceInfo = performanceInfoRepository.getPriceInfoByPerformanceId(id).toPerformancePrice()
            val updatedPriceInfo = currentPriceInfo.changeDiscountPrice(discountPrice)

            updateDiscountPrice(updatedPriceInfo)
            saveDiscountChangeHistory(updatedPriceInfo)
        }

    private fun updateDiscountPrice(updatedPriceInfo: PerformancePrice) {
        performanceInfoRepository.updateDiscountPrice(
            performanceId = updatedPriceInfo.performanceId,
            performanceDiscountId = updatedPriceInfo.performanceDiscountId,
            discountPrice = updatedPriceInfo.performanceDiscountPrice,
        )
    }

    private fun saveDiscountChangeHistory(updatedPriceInfo: PerformancePrice) {
        performanceInfoHistoryRepository.saveDiscountChangeHistory(
            performanceId = updatedPriceInfo.performancePriceId,
            performanceDiscountId = updatedPriceInfo.performanceDiscountId,
            discountPrice = updatedPriceInfo.performanceDiscountPrice,
        )
    }
}

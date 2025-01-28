package com.hunhui.ticketworld.application

import com.hunhui.ticketworld.application.dto.request.DiscountCreateRequest
import com.hunhui.ticketworld.application.dto.request.DiscountFindRequest
import com.hunhui.ticketworld.application.dto.response.DiscountListResponse
import com.hunhui.ticketworld.domain.discount.Discount
import com.hunhui.ticketworld.domain.discount.DiscountRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DiscountService(
    private val discountRepository: DiscountRepository,
) {
    fun createDiscount(
        performanceId: UUID,
        discountCreateRequest: DiscountCreateRequest,
    ) {
        discountRepository.save(discountCreateRequest.domain(performanceId))
    }

    fun findAllApplicableDiscounts(
        performanceId: UUID,
        discountFindRequest: DiscountFindRequest,
    ): DiscountListResponse {
        val roundId: UUID = discountFindRequest.performanceRoundId
        val priceIds: List<UUID> = discountFindRequest.reservePriceIds
        val discounts: List<Discount> = discountRepository.findAllByPerformanceId(performanceId)
        val applicableDiscountsWithPriceIdList: MutableList<DiscountListResponse.DiscountsWithPriceIdResponse> = mutableListOf()
        for (priceId in priceIds) {
            val applicableDiscounts = discounts.findApplicable(priceId = priceId, roundId = roundId)
            applicableDiscountsWithPriceIdList.add(applicableDiscounts.toDto(priceId = priceId))
        }
        return DiscountListResponse(applicableDiscountsWithPriceIdList)
    }

    private fun List<Discount>.findApplicable(
        priceId: UUID,
        roundId: UUID,
    ): List<Discount> =
        this.filter {
            it.isApplicable(roundId = roundId, priceId = priceId)
        }

    private fun List<Discount>.toDto(priceId: UUID): DiscountListResponse.DiscountsWithPriceIdResponse =
        DiscountListResponse.DiscountsWithPriceIdResponse(
            performancePriceId = priceId,
            discountResponses =
                this.map {
                    DiscountListResponse.DiscountResponse(
                        discountId = it.id,
                        discountName = it.discountName,
                        discountRate = it.discountRate.rate,
                        applyCountType = it.applyCount.type,
                        applyCountAmount = it.applyCount.amount,
                    )
                },
        )
}

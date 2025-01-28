package com.hunhui.ticketworld.application.dto.request

import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.domain.reservation.PaymentMethod
import com.hunhui.ticketworld.domain.reservation.exception.ReservationErrorCode
import java.util.UUID

data class ConfirmReserveRequest(
    val reservationIds: List<UUID>,
    val paymentInfos: List<PaymentInfoRequest>,
    val paymentMethod: PaymentMethod,
    val userId: UUID,
) {
    init {
        if (paymentInfos.isEmpty()) throw BusinessException(ReservationErrorCode.INVALID_RESERVE_REQUEST)
        if (paymentInfos.isDiscountIdDuplicatedInSamePriceId()) throw BusinessException(ReservationErrorCode.INVALID_RESERVE_REQUEST)
    }

    data class PaymentInfoRequest(
        val performancePriceId: UUID,
        val reservationCount: Int,
        val discountId: UUID?,
    )

    /** 같은 가격 내에서 할인 id가 겹치면 True */
    private fun List<PaymentInfoRequest>.isDiscountIdDuplicatedInSamePriceId(): Boolean =
        groupBy { it.performancePriceId }.any {
            val paymentInfos: List<PaymentInfoRequest> = it.value
            val discountIds: List<UUID?> = paymentInfos.map { paymentInfo -> paymentInfo.discountId }
            discountIds.distinct().size != paymentInfos.size
        }
}

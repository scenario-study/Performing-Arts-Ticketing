package com.performance.web.api.reservation.domain

import com.performance.web.api.common.domain.BusinessException
import com.performance.web.api.discount.domain.DiscountFactor
import com.performance.web.api.discount.domain.DiscountPolicy

class Seat(
    id: Long = 0L,
    seatClass: SeatClass,
    seatStatus: SeatStatus,
    seatPosition: SeatPosition,
) {

    private val _id: Long = id
    private val _seatClass: SeatClass = seatClass
    private var _seatStatus: SeatStatus = seatStatus
    private val _seatPosition: SeatPosition = seatPosition

    fun reserve(
        discountPolicy: DiscountPolicy,
        discountFactor: DiscountFactor,
    ): Ticket {
        if (!_seatStatus.canReserve()) {
            throw BusinessException("이미 예약된 좌석입니다.")
        }
        _seatStatus = SeatStatus.RESERVED

        return Ticket(
            totalAmount = _seatClass.calculateTotalAmount(discountPolicy, discountFactor), // 티켓을 구매한 당시의 가격
            regularPrice = _seatClass.getPrice(), // Ticket 을 구매한 당시의 정가
            seat = this,
            appliedDiscountPolicy = discountPolicy,
        )
    }

    fun getId(): Long = _id

    fun getSeatClass(): SeatClass = _seatClass

    fun getSeatStatus(): SeatStatus = _seatStatus

    fun getSeatPosition(): SeatPosition = _seatPosition
}

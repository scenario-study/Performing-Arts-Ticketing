package com.hunhui.ticketworld.application

import com.hunhui.ticketworld.application.dto.request.ConfirmReserveRequest
import com.hunhui.ticketworld.application.dto.request.TempReserveRequest
import com.hunhui.ticketworld.application.dto.response.ConfirmReserveResponse
import com.hunhui.ticketworld.application.dto.response.ReservationListResponse
import com.hunhui.ticketworld.common.error.BusinessException
import com.hunhui.ticketworld.common.vo.Money
import com.hunhui.ticketworld.domain.discount.Discount
import com.hunhui.ticketworld.domain.discount.DiscountRepository
import com.hunhui.ticketworld.domain.payment.Payment
import com.hunhui.ticketworld.domain.payment.PaymentInfo
import com.hunhui.ticketworld.domain.payment.PaymentRepository
import com.hunhui.ticketworld.domain.performance.PerformanceRepository
import com.hunhui.ticketworld.domain.reservation.Reservation
import com.hunhui.ticketworld.domain.reservation.ReservationRepository
import com.hunhui.ticketworld.domain.reservation.exception.ReservationErrorCode
import com.hunhui.ticketworld.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ReservationService(
    private val performanceRepository: PerformanceRepository,
    private val reservationRepository: ReservationRepository,
    private val userRepository: UserRepository,
    private val discountRepository: DiscountRepository,
    private val paymentRepository: PaymentRepository,
) {
    fun findAll(
        roundId: UUID,
        areaId: UUID,
    ): ReservationListResponse {
        val reservationList: List<Reservation> = reservationRepository.findAllByRoundIdAndAreaId(roundId, areaId)
        return ReservationListResponse.from(reservationList)
    }

    @Transactional
    fun tempReserve(tempReserveRequest: TempReserveRequest) {
        tempReserveRequest.validate()
        val updatedReservations: List<Reservation> =
            tempReserveRequest.reservationIds.map { reservationId ->
                val reservation: Reservation = reservationRepository.getById(reservationId)
                reservation.tempReserve(tempReserveRequest.userId)
            }
        reservationRepository.saveAll(updatedReservations)
    }

    @Transactional
    fun confirmReserve(confirmReserveRequest: ConfirmReserveRequest): ConfirmReserveResponse {
        val reservationIds = confirmReserveRequest.reservationIds
        val reservations: List<Reservation> = reservationIds.map { reservationRepository.getById(it) }
        val roundId = reservations.getRoundId()
        val priceMap: Map<UUID, Money> = reservations.getPriceMap()

        // discount, price, count
        // roundId, priceId

        // 예약 가능한지 확인
        reservations.checkAllCanReserve(confirmReserveRequest.userId)

        // 예매와 결제 정보의 좌석 별 티켓 수가 일치하는지 확인
        // ex) 예매 목록의 VIP 좌석이 3개, S좌석이 1개 일 때, 결제 정보의 VIP 좌석이 3개, S좌석이 1개여야 함
        val reservationsPriceIdCount: Map<UUID, Int> = reservations.getPriceCountByReservations()
        val paymentInfosPriceIdCount: Map<UUID, Int> = confirmReserveRequest.paymentInfos.getPriceCountByPaymentInfos()
        if (reservationsPriceIdCount != paymentInfosPriceIdCount) throw BusinessException(ReservationErrorCode.INVALID_RESERVE_REQUEST)

        // 할인 적용 가능한지 확인
        val discountMap: Map<UUID, Discount> =
            confirmReserveRequest.paymentInfos.mapNotNull { it.discountId }.toSet().associateWith { discountId ->
                discountRepository.getById(discountId)
            }
        val paymentInfos: List<PaymentInfo> =
            confirmReserveRequest.paymentInfos.map { paymentInfo ->
                val discount: Discount? = discountMap[paymentInfo.discountId]
                val price = priceMap[paymentInfo.performancePriceId]!!
                if (discount == null) {
                    PaymentInfo(
                        id = UUID.randomUUID(),
                        performancePriceId = paymentInfo.performancePriceId,
                        reservationCount = paymentInfo.reservationCount,
                        discountId = paymentInfo.discountId,
                        paymentAmount =
                            price * paymentInfo.reservationCount,
                    )
                } else if (discount.isApplicable(roundId, paymentInfo.performancePriceId)) {
                    val paymentAmount = discount.apply(price, paymentInfo.reservationCount)
                    PaymentInfo(
                        id = UUID.randomUUID(),
                        performancePriceId = paymentInfo.performancePriceId,
                        reservationCount = paymentInfo.reservationCount,
                        discountId = paymentInfo.discountId,
                        paymentAmount = paymentAmount,
                    )
                } else {
                    throw BusinessException(ReservationErrorCode.CANNOT_RESERVE)
                }
            }

        val payment: Payment =
            Payment(
                id = UUID.randomUUID(),
                userId = confirmReserveRequest.userId,
                paymentMethod = confirmReserveRequest.paymentMethod,
                paymentInfos = paymentInfos,
            )
        paymentRepository.save(payment)
        val confirmedReservations: List<Reservation> = reservations.map { it.confirmReserve(confirmReserveRequest.userId, payment.id) }
        reservationRepository.saveAll(confirmedReservations)
        return ConfirmReserveResponse(paymentId = payment.id)
    }

    private fun TempReserveRequest.validate() {
        userRepository.getById(userId)
        val reservationCount = performanceRepository.getById(performanceId).reservationCount
        if (reservationCount < reservationIds.size) throw BusinessException(ReservationErrorCode.RESERVATION_COUNT_EXCEED)
    }

    private fun List<Reservation>.checkAllCanReserve(userId: UUID) {
        if (any { !it.canConfirmReserve(userId) }) throw BusinessException(ReservationErrorCode.CANNOT_RESERVE)
    }

    private fun List<Reservation>.getPriceCountByReservations(): Map<UUID, Int> =
        this
            .groupBy { it.performancePriceId }
            .mapValues { (_, reservations) -> reservations.size }

    private fun List<ConfirmReserveRequest.PaymentInfoRequest>.getPriceCountByPaymentInfos(): Map<UUID, Int> =
        this
            .groupBy { it.performancePriceId }
            .mapValues { (_, paymentInfos) -> paymentInfos.sumOf { it.reservationCount } }

    private fun List<Reservation>.getRoundId(): UUID {
        val roundIds: Set<UUID> = this.map { it.roundId }.toSet()
        if (roundIds.size != 1) throw BusinessException(ReservationErrorCode.INVALID_RESERVE_REQUEST)
        return roundIds.first()
    }

    private fun List<Reservation>.getPriceMap(): Map<UUID, Money> = this.associate { it.performancePriceId to it.price }
}

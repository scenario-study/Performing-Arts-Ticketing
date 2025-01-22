package com.performance.web.api.reservation.controller.dto

import com.performance.web.api.reservation.domain.Reservation
import com.performance.web.api.reservation.domain.Seat
import com.performance.web.api.reservation.domain.Ticket
import java.time.LocalDate
import java.time.LocalTime

data class ReservationApiResponse(
    val id: Long,
    val performanceName: String,
    val startDate: LocalDate,
    val starTime: LocalTime,
    val endTime: LocalTime,
    val tickets: List<TicketApiResponse>
) {


    companion object {
        fun from(reservation: Reservation): ReservationApiResponse {
            return ReservationApiResponse(
                id = reservation.getId(),
                performanceName = reservation.getSession().getPerformance().getName(),
                startDate = reservation.getSession().getStartDateTime().toLocalDate(),
                starTime = reservation.getSession().getStartDateTime().toLocalTime(),
                endTime = reservation.getSession().getStartDateTime().plusMinutes(
                    reservation.getSession().getPerformance().getRunTime(),
                ).toLocalTime(),
                tickets = reservation.getTickets().map { ticket ->
                    TicketApiResponse.from(ticket)
                },
            )
        }
    }

    data class TicketApiResponse(
        val id: Long,
        val seatInfo: SeatInfoApiResponse,
        val totalAmount: Long,
        val regularPrice: Long,
        val discountName: String,
    ) {
        companion object {

            fun from(ticket: Ticket): TicketApiResponse {
                return TicketApiResponse(
                    id = ticket.getId(),
                    seatInfo = SeatInfoApiResponse.from(ticket.getSeat()),
                    totalAmount = ticket.getTotalAmount().longValue(),
                    regularPrice = ticket.getRegularPrice().longValue(),
                    discountName = ticket.getAppliedDiscountPolicy().getName(),
                )
            }
        }
    }


    data class SeatInfoApiResponse(
        val id: Long,
        val seatClassType: String,
        val floor: Int,
        val row: Int,
        val column: Int,
    ) {
        companion object {
            fun from(seat: Seat): SeatInfoApiResponse {
                return SeatInfoApiResponse(
                    id = seat.getId(),
                    seatClassType = seat.getSeatClass().getClassType(),
                    floor = seat.getSeatPosition().floor,
                    row = seat.getSeatPosition().row,
                    column = seat.getSeatPosition().column,
                )
            }
        }
    }


}

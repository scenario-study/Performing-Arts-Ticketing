package com.performance.web.api.seat.infrastructure.jpa

import com.performance.web.api.session.infrastructure.jpa.SessionEntity
import com.performance.web.api.seat.domain.Seat
import jakarta.persistence.*

@Entity
@Table(name = "seat")
class SeatEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long,

    @Enumerated(EnumType.STRING)
    var seatStatus: SeatStatusEntity,

    @Embedded
    var seatPosition: SeatPositionEntity,

    @Embedded
    var seatClass: SeatClassEntity,

    var sessionId : Long

    ) {

    fun toDomain(): Seat {

        return Seat(
            id = id,
            seatClass = seatClass.toDomain(),
            seatStatus = seatStatus.toDomain(),
            seatPosition = seatPosition.toDomain(),
            sessionId = sessionId
        )
    }


    companion object {

        fun fromDomain(seat: Seat): SeatEntity {
            return SeatEntity(
                id = seat.getId(),
                seatStatus = SeatStatusEntity.fromDomain(seat.getSeatStatus()),
                seatPosition = SeatPositionEntity.fromDomain(seat.getSeatPosition()),
                seatClass = SeatClassEntity.fromDomain(seat.getSeatClass()),
                sessionId = seat.getSessionId(),
            )
        }
    }
}

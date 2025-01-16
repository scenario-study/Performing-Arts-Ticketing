package com.hunhui.ticketworld.infra.jpa.repository

import com.hunhui.ticketworld.domain.seat.Seat
import com.hunhui.ticketworld.domain.seat.SeatArea
import com.hunhui.ticketworld.domain.seat.SeatAreaRepository
import com.hunhui.ticketworld.domain.seat.exception.SeatAreaNotFoundException
import com.hunhui.ticketworld.infra.jpa.entity.SeatAreaEntity
import com.hunhui.ticketworld.infra.jpa.entity.SeatEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
internal class SeatAreaRepositoryImpl(
    private val seatAreaJpaRepository: SeatAreaJpaRepository,
) : SeatAreaRepository {
    override fun getById(id: UUID): SeatArea? = seatAreaJpaRepository.findByIdOrNull(id)?.domain ?: throw SeatAreaNotFoundException()

    override fun findByPerformanceId(performanceId: UUID): List<SeatArea> =
        seatAreaJpaRepository.findByPerformanceId(performanceId).map {
            it.domain
        }

    override fun save(seatArea: SeatArea) {
        seatAreaJpaRepository.save(seatArea.entity)
    }

    private val SeatAreaEntity.domain: SeatArea
        get() =
            SeatArea(
                id = id,
                performanceId = performanceId,
                floorName = floorName,
                areaName = areaName,
                width = width,
                height = height,
                seats =
                    seats.map {
                        Seat(
                            id = it.id,
                            gradeId = it.gradeId,
                            seatName = it.seatName,
                            x = it.x,
                            y = it.y,
                        )
                    },
            )

    private val SeatArea.entity: SeatAreaEntity
        get() =
            SeatAreaEntity(
                id = id,
                performanceId = performanceId,
                floorName = floorName,
                areaName = areaName,
                width = width,
                height = height,
                seats =
                    seats.map {
                        SeatEntity(
                            id = it.id,
                            gradeId = it.gradeId,
                            seatName = it.seatName,
                            x = it.x,
                            y = it.y,
                        )
                    },
            )
}

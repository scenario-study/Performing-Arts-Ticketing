package com.performance.web.api.reservation.infrastructure.jpa

import com.performance.web.api.reservation.domain.Performance
import jakarta.persistence.*
import org.hibernate.Hibernate
import java.time.LocalDate

@Entity
@Table(name = "performance")
class PerformanceEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var runTime: Long,

    @Column(nullable = false)
    var startDate: LocalDate,

    @Column(nullable = false)
    var endDate: LocalDate,

    @Column(nullable = false)
    var description: String,

    @OneToMany(mappedBy = "performance", fetch = FetchType.LAZY)
    var seatClasses: MutableSet<SeatClassEntity> = mutableSetOf(),
) {


    fun toDomain(): Performance {
        if (!Hibernate.isInitialized(seatClasses)) {
            // Lazy 로딩이 안되도록 리턴
            return Performance(
                id = id,
                name = name,
                runTimeInMinutes = runTime,
                startDate = startDate,
                endDate = endDate,
                description = description,
            )
        }
        return Performance(
            id = id,
            name = name,
            runTimeInMinutes = runTime,
            startDate = startDate,
            endDate = endDate,
            description = description,
            seatClasses = seatClasses.map { it.toDomain() } // Lazy 로딩된 seatClasses 처리
        )
    }

    companion object {

        fun fromDomain(performance: Performance): PerformanceEntity {
            return PerformanceEntity(
                id = performance.getId(),
                name = performance.getName(),
                runTime = performance.getRunTime(),
                startDate = performance.getStartDate(),
                endDate = performance.getEndDate(),
                description = performance.getDescription(),
                seatClasses = performance.getSeatClasses().map { SeatClassEntity.fromDomain(it) }.toMutableSet(),
            )
        }
    }
}

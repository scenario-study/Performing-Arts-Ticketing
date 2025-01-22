package com.performance.web.api.discount.infrastructure

import com.performance.web.api.discount.domain.*
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Component
class MemoryBasedDiscountPolicyRepositoryImpl : DiscountPolicyRepository {

    private final val discountPolicyStore = ConcurrentHashMap<Long, DiscountPolicy>(
        mapOf(
            1L to PercentDiscountPolicy(
                id = 1L,
                name = "새해 기념 할인",
                conditions = arrayOf(
                    DateRangeCondition(
                        startDateTime = LocalDateTime.of(2025, 1, 1, 0, 0),
                        endDateTime = LocalDateTime.of(2025, 1, 2, 0, 0),
                    ),
                ),
                percent = 0.3,
            ),
            2L to PercentDiscountPolicy(
                id = 2L,
                name = "전역 군인 할인(전역증 지참)",
                conditions = arrayOf(
                    OfflineCheckCondition(),
                ),
                percent = 0.5,
            ),
            3L to PercentDiscountPolicy(
                id = 3L,
                name = "오전 공연 할인 (오전 5시~9시 시작 공연), 20% 할인",
                conditions = arrayOf(
                    PeriodCondition(
                        startDateTime = LocalDateTime.of(2025, 1, 1, 5, 0),
                        endDateTime = LocalDateTime.of(2025, 1, 2, 9, 1),
                    ),
                ),
                percent = 0.2,
            ),
        ),
    )


    override fun findById(id: Long): Optional<DiscountPolicy> {
        return Optional.ofNullable(discountPolicyStore[id])
    }

}

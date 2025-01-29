package com.cd18.domain.metrics.repository

import com.cd18.domain.metrics.dto.TargetCountSummaryDto
import com.cd18.domain.metrics.enums.ActionType
import com.cd18.domain.metrics.enums.TargetType
import java.time.LocalDateTime

interface UserActionLogRepository {
    fun save(
        userId: Long?,
        actionType: ActionType,
        targetId: Long?,
        targetType: TargetType,
        actionDetail: String?,
    )

    fun getTargetIdCountSummary(
        userId: Long? = null,
        actionType: ActionType?,
        targetType: TargetType?,
        startTime: LocalDateTime?,
        endTime: LocalDateTime?,
    ): List<TargetCountSummaryDto>
}

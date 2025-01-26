package com.cd18.domain.metrics.repository

import com.cd18.domain.metrics.enums.ActionType
import com.cd18.domain.metrics.enums.TargetType

interface UserActionLogRepository {
    fun save(
        userId: Long?,
        actionType: ActionType,
        targetId: Long?,
        targetType: TargetType,
        actionDetail: String?,
    )
}

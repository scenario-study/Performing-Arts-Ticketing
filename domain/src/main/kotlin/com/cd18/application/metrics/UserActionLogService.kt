package com.cd18.application.metrics

import com.cd18.domain.metrics.enums.ActionType
import com.cd18.domain.metrics.enums.TargetType

interface UserActionLogService {
    fun logAction(
        userId: Long?,
        actionType: ActionType,
        targetType: TargetType,
        targetId: Long?,
        actionDetail: String?,
    )
}

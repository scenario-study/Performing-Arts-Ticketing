package com.cd18.domain.metrics.dto

import com.cd18.domain.metrics.enums.ActionType
import com.cd18.domain.metrics.enums.TargetType

data class TargetCountSummaryDto(
    val targetType: TargetType,
    val actionType: ActionType,
    val targetId: Long,
    val count: Long,
)
